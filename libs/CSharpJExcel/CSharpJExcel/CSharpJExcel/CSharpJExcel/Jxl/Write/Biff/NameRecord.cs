/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
***************************************************************************/

// Port to C# 
// Chris Laforet
// Wachovia, a Wells-Fargo Company
// Feb 2010


using CSharpJExcel.Jxl.Biff;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * A name record.  Simply takes the binary data from the name
	 * record read in
	 */
	public class NameRecord : WritableRecordData
		{
		// The logger
		//private static Logger logger = Logger.getLogger(NameRecord.class);
		/**
		 * The binary data for output to file
		 */
		private byte[] data;

		/**
		 * The name
		 */
		private string name;

		/**
		 * The built in name
		 */
		private BuiltInName builtInName;

		/**
		 * The index into the name table
		 */
		private int index;

		/**
		 * The 0-based index sheet reference for a record name
		 *     0 is for a global reference
		 */
		private int sheetRef = 0;

		/**
		 * Modified flag
		 */
		private bool modified;

		/** 
		 * A nested class to hold range information
		 */
		public class NameRange
			{
			private int columnFirst;
			private int rowFirst;
			private int columnLast;
			private int rowLast;
			private int externalSheet;

			public NameRange(CSharpJExcel.Jxl.Read.Biff.NameRecord.NameRange nr)
				{
				columnFirst = nr.getFirstColumn();
				rowFirst = nr.getFirstRow();
				columnLast = nr.getLastColumn();
				rowLast = nr.getLastRow();
				externalSheet = nr.getExternalSheet();
				}

			/**
			 * Create a new range for the name record.
			 */
			public NameRange(int extSheet,
					  int theStartRow,
					  int theEndRow,
					  int theStartCol,
					  int theEndCol)
				{
				columnFirst = theStartCol;
				rowFirst = theStartRow;
				columnLast = theEndCol;
				rowLast = theEndRow;
				externalSheet = extSheet;
				}

			public int getFirstColumn() { return columnFirst; }
			public int getFirstRow() { return rowFirst; }
			public int getLastColumn() { return columnLast; }
			public int getLastRow() { return rowLast; }
			public int getExternalSheet() { return externalSheet; }

			public void incrementFirstRow() { rowFirst++; }
			public void incrementLastRow() { rowLast++; }
			public void decrementFirstRow() { rowFirst--; }
			public void decrementLastRow() { rowLast--; }
			public void incrementFirstColumn() { columnFirst++; }
			public void incrementLastColumn() { columnLast++; }
			public void decrementFirstColumn() { columnFirst--; }
			public void decrementLastColumn() { columnLast--; }

			public byte[] getData()
				{
				byte[] d = new byte[10];

				// Sheet index
				IntegerHelper.getTwoBytes(externalSheet, d, 0);

				// Starting row
				IntegerHelper.getTwoBytes(rowFirst, d, 2);

				// End row
				IntegerHelper.getTwoBytes(rowLast, d, 4);

				// Start column
				IntegerHelper.getTwoBytes(columnFirst & 0xff, d, 6);

				// End columns
				IntegerHelper.getTwoBytes(columnLast & 0xff, d, 8);

				return d;
				}
			}

		/**
		 * The ranges covered by this name
		 */
		private NameRange[] ranges;

		// Constants which refer to the parse tokens after the string
		private const int cellReference = 0x3a;
		private const int areaReference = 0x3b;
		private const int subExpression = 0x29;
		private const int union = 0x10;

		// An empty range
		private static readonly NameRange EMPTY_RANGE = new NameRange(0, 0, 0, 0, 0);

		/**
		 * Constructor - used when copying sheets
		 *
		 * @param index the index into the name table
		 */
		public NameRecord(CSharpJExcel.Jxl.Read.Biff.NameRecord sr, int ind)
			: base(Type.NAME)
			{
			data = sr.getData();
			name = sr.getName();
			sheetRef = sr.getSheetRef();
			index = ind;
			modified = false;

			// Copy the ranges
			CSharpJExcel.Jxl.Read.Biff.NameRecord.NameRange[] r = sr.getRanges();
			ranges = new NameRange[r.Length];
			for (int i = 0; i < ranges.Length; i++)
				{
				ranges[i] = new NameRange(r[i]);
				}
			}

		/**
		 * Create a new name record with the given information.
		 * 
		 * @param theName      Name to be created.
		 * @param theIndex     Index of this name.
		 * @param extSheet     External sheet index this name refers to.
		 * @param theStartRow  First row this name refers to.
		 * @param theEndRow    Last row this name refers to.
		 * @param theStartCol  First column this name refers to.
		 * @param theEndCol    Last column this name refers to.
		 * @param global       TRUE if this is a global name
		 */
		public NameRecord(string theName,
				   int theIndex,
				   int extSheet,
				   int theStartRow,
				   int theEndRow,
				   int theStartCol,
				   int theEndCol,
				   bool global)
			: base(Type.NAME)
			{
			name = theName;
			index = theIndex;
			sheetRef = global ? 0 : index + 1; // 0 indicates a global name, otherwise
			// the 1-based index of the sheet

			ranges = new NameRange[1];
			ranges[0] = new NameRange(extSheet,
									  theStartRow,
									  theEndRow,
									  theStartCol,
									  theEndCol);
			modified = true;
			}

		/**
		 * Create a new name record with the given information.
		 * 
		 * @param theName      Name to be created.
		 * @param theIndex     Index of this name.
		 * @param extSheet     External sheet index this name refers to.
		 * @param theStartRow  First row this name refers to.
		 * @param theEndRow    Last row this name refers to.
		 * @param theStartCol  First column this name refers to.
		 * @param theEndCol    Last column this name refers to.
		 * @param global       TRUE if this is a global name
		 */
		public NameRecord(BuiltInName theName,
				   int theIndex,
				   int extSheet,
				   int theStartRow,
				   int theEndRow,
				   int theStartCol,
				   int theEndCol,
				   bool global)
			: base(Type.NAME)
			{
			builtInName = theName;
			index = theIndex;
			sheetRef = global ? 0 : index + 1; // 0 indicates a global name, otherwise
			// the 1-based index of the sheet

			ranges = new NameRange[1];
			ranges[0] = new NameRange(extSheet,
									  theStartRow,
									  theEndRow,
									  theStartCol,
									  theEndCol);
			}

		/**
		 * Create a new name record with the given information for 2-range entities.
		 * 
		 * @param theName      Name to be created.
		 * @param theIndex     Index of this name.
		 * @param extSheet     External sheet index this name refers to.
		 * @param theStartRow  First row this name refers to.
		 * @param theEndRow    Last row this name refers to.
		 * @param theStartCol  First column this name refers to.
		 * @param theEndCol    Last column this name refers to.
		 * @param theStartRow2 First row this name refers to (2nd instance).
		 * @param theEndRow2   Last row this name refers to (2nd instance).
		 * @param theStartCol2 First column this name refers to (2nd instance).
		 * @param theEndCol2   Last column this name refers to (2nd instance). 
		 * @param global       TRUE if this is a global name
		 */
		public NameRecord(BuiltInName theName,
				   int theIndex,
				   int extSheet,
				   int theStartRow,
				   int theEndRow,
				   int theStartCol,
				   int theEndCol,
				   int theStartRow2,
				   int theEndRow2,
				   int theStartCol2,
				   int theEndCol2,
				   bool global)
			: base(Type.NAME)
			{
			builtInName = theName;
			index = theIndex;
			sheetRef = global ? 0 : index + 1; // 0 indicates a global name, otherwise
			// the 1-based index of the sheet

			ranges = new NameRange[2];
			ranges[0] = new NameRange(extSheet,
									  theStartRow,
									  theEndRow,
									  theStartCol,
									  theEndCol);
			ranges[1] = new NameRange(extSheet,
									  theStartRow2,
									  theEndRow2,
									  theStartCol2,
									  theEndCol2);
			}


		/**
		 * Gets the binary data for output to file
		 *
		 * @return the binary data
		 */
		public override byte[] getData()
			{
			if (data != null && !modified)
				{
				// this is a copy
				return data;
				}

			const int NAME_HEADER_LENGTH = 15;
			const byte AREA_RANGE_LENGTH = 11;
			const byte AREA_REFERENCE = 0x3b;

			int detailLength;

			if (ranges.Length > 1)
				detailLength = (ranges.Length * AREA_RANGE_LENGTH) + 4;
			else
				detailLength = AREA_RANGE_LENGTH;

			int length = NAME_HEADER_LENGTH + detailLength;
			length += builtInName != null ? 1 : name.Length;
			data = new byte[length];

			// Options
			int options = 0;

			if (builtInName != null)
				{
				options |= 0x20;
				}
			IntegerHelper.getTwoBytes(options, data, 0);

			// Keyboard shortcut
			data[2] = 0;

			// Length of the name in chars
			if (builtInName != null)
				{
				data[3] = (byte)0x1;
				}
			else
				{
				data[3] = (byte)name.Length;
				}

			// Size of the definitions
			IntegerHelper.getTwoBytes(detailLength, data, 4);

			// Sheet index
			IntegerHelper.getTwoBytes(sheetRef, data, 6);
			IntegerHelper.getTwoBytes(sheetRef, data, 8);

			// Byte 10-13 are optional lengths [0,0,0,0]    
			// Byte 14 is length of name which is not used.    

			// The name
			if (builtInName != null)
				data[15] = (byte)builtInName.getValue();
			else
				StringHelper.getBytes(name, data, 15);

			// The actual range definition.
			int pos = builtInName != null ? 16 : name.Length + 15;

			// If there are multiple ranges for the name, we must specify a 
			// subExpression type rather than areaReference and then put out 
			// multiple areaReference entries with an end byte.
			if (ranges.Length > 1)
				{
				data[pos++] = subExpression;
				// Length of remaining bytes excluding itself
				IntegerHelper.getTwoBytes(detailLength - 3, data, pos);
				pos += 2;
				byte[] rd;
				for (int i = 0; i < ranges.Length; i++)
					{
					data[pos++] = areaReference;
					rd = ranges[i].getData();
					System.Array.Copy(rd, 0, data, pos, rd.Length);
					pos += rd.Length;
					}
				data[pos] = 0x10;
				}
			else
				{
				// Range format - area
				data[pos] = areaReference;

				// The range data
				byte[] rd = ranges[0].getData();
				System.Array.Copy(rd, 0, data, pos + 1, rd.Length);
				}

			return data;
			}

		/**
		 * Accessor for the name 
		 *
		 * @return the name
		 */
		public string getName()
			{
			return name;
			}

		/**
		 * Accessor for the index of this name in the name table
		 *
		 * @return the index of this name in the name table
		 */
		public int getIndex()
			{
			return index;
			}

		/**
		 * The 0-based index sheet reference for a record name
		 *     0 is for a global reference
		 *
		 * @return the sheet reference for name formula
		 */
		public int getSheetRef()
			{
			return sheetRef;
			}

		/**
		 * Set the index sheet reference for a record name
		 *     0 is for a global reference
		 *
		 */
		public void setSheetRef(int i)
			{
			sheetRef = i;
			IntegerHelper.getTwoBytes(sheetRef, data, 8);
			}

		/**
		 * Gets the array of ranges for this name
		 * @return the ranges
		 */
		public NameRange[] getRanges()
			{
			return ranges;
			}

		/**
		 * Called when a row is inserted on the 
		 *
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param row the column number which was inserted
		 */
		public virtual void rowInserted(int sheetIndex, int row)
			{
			for (int i = 0; i < ranges.Length; i++)
				{
				if (sheetIndex != ranges[i].getExternalSheet())
					{
					continue; // shame on me - this is no better than a goto
					}

				if (row <= ranges[i].getFirstRow())
					{
					ranges[i].incrementFirstRow();
					modified = true;
					}

				if (row <= ranges[i].getLastRow())
					{
					ranges[i].incrementLastRow();
					modified = true;
					}
				}
			}

		/**
		 * Called when a row is removed on the worksheet
		 *
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param row the column number which was inserted
		 * @reeturn TRUE if the name is to be removed entirely, FALSE otherwise
		 */
		public virtual bool rowRemoved(int sheetIndex, int row)
			{
			for (int i = 0; i < ranges.Length; i++)
				{
				if (sheetIndex != ranges[i].getExternalSheet())
					{
					continue; // shame on me - this is no better than a goto
					}

				if (row == ranges[i].getFirstRow() && row == ranges[i].getLastRow())
					{
					// remove the range
					ranges[i] = EMPTY_RANGE;
					}

				if (row < ranges[i].getFirstRow() && row > 0)
					{
					ranges[i].decrementFirstRow();
					modified = true;
					}

				if (row <= ranges[i].getLastRow())
					{
					ranges[i].decrementLastRow();
					modified = true;
					}
				}

			// If all ranges are empty, then remove the name
			int emptyRanges = 0;
			for (int i = 0; i < ranges.Length; i++)
				{
				if (ranges[i] == EMPTY_RANGE)
					{
					emptyRanges++;
					}
				}

			if (emptyRanges == ranges.Length)
				{
				return true;
				}

			// otherwise just remove the empty ones
			NameRange[] newRanges = new NameRange[ranges.Length - emptyRanges];
			for (int i = 0; i < ranges.Length; i++)
				{
				if (ranges[i] != EMPTY_RANGE)
					{
					newRanges[i] = ranges[i];
					}
				}

			ranges = newRanges;

			return false;
			}

		/**
		 * Called when a row is removed on the worksheet
		 *
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param row the column number which was inserted
		 * @reeturn TRUE if the name is to be removed entirely, FALSE otherwise
		 */
		public virtual bool columnRemoved(int sheetIndex, int col)
			{
			for (int i = 0; i < ranges.Length; i++)
				{
				if (sheetIndex != ranges[i].getExternalSheet())
					{
					continue; // shame on me - this is no better than a goto
					}

				if (col == ranges[i].getFirstColumn() && col ==
					ranges[i].getLastColumn())
					{
					// remove the range
					ranges[i] = EMPTY_RANGE;
					}

				if (col < ranges[i].getFirstColumn() && col > 0)
					{
					ranges[i].decrementFirstColumn();
					modified = true;
					}

				if (col <= ranges[i].getLastColumn())
					{
					ranges[i].decrementLastColumn();
					modified = true;
					}
				}

			// If all ranges are empty, then remove the name
			int emptyRanges = 0;
			for (int i = 0; i < ranges.Length; i++)
				{
				if (ranges[i] == EMPTY_RANGE)
					{
					emptyRanges++;
					}
				}

			if (emptyRanges == ranges.Length)
				{
				return true;
				}

			// otherwise just remove the empty ones
			NameRange[] newRanges = new NameRange[ranges.Length - emptyRanges];
			for (int i = 0; i < ranges.Length; i++)
				{
				if (ranges[i] != EMPTY_RANGE)
					{
					newRanges[i] = ranges[i];
					}
				}

			ranges = newRanges;

			return false;
			}


		/**
		 * Called when a row is inserted on the 
		 *
		 * @param sheetIndex the sheet index on which the column was inserted
		 * @param col the column number which was inserted
		 */
		public virtual void columnInserted(int sheetIndex, int col)
			{
			for (int i = 0; i < ranges.Length; i++)
				{
				if (sheetIndex != ranges[i].getExternalSheet())
					{
					continue; // shame on me - this is no better than a goto
					}

				if (col <= ranges[i].getFirstColumn())
					{
					ranges[i].incrementFirstColumn();
					modified = true;
					}

				if (col <= ranges[i].getLastColumn())
					{
					ranges[i].incrementLastColumn();
					modified = true;
					}
				}
			}
		}
	}




