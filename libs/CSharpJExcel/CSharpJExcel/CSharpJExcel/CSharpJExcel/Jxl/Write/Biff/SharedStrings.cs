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

using System.Collections;
using System.Collections.Generic;


namespace CSharpJExcel.Jxl.Write.Biff
	{
	/**
	 * The list of available shared strings.  This class contains
	 * the labels used for the entire spreadsheet
	 */
	public class SharedStrings
		{
		/**
		 * All the strings in the spreadsheet, keyed on the string itself
		 */
		private Dictionary<string,int> strings;

		/**
		 * Contains the same strings, held in a list
		 */
		private ArrayList stringList;

		/**
		 * The total occurrence of strings in the workbook
		 */
		private int totalOccurrences;

		/**
		 * Constructor
		 */
		public SharedStrings()
			{
			strings = new Dictionary<string,int>(100);
			stringList = new ArrayList(100);
			totalOccurrences = 0;
			}

		/**
		 * Gets the index for the string passed in.  If the string is already
		 * present, then returns the index of that string, otherwise
		 * creates a new key-index mapping
		 *
		 * @param s the string whose index we want
		 * @return the index of the string
		 */
		public int getIndex(string s)
			{
			int i;
			if (!strings.ContainsKey(s))
				{
				i = strings.Count;
				strings.Add(s, i);
				stringList.Add(s);
				}
			else
				i = strings[s];
			totalOccurrences++;

			return i;
			}

		/**
		 * Gets the string at the specified index
		 *
		 * @param i the index of the string
		 * @return the string at the specified index
		 */
		public string get(int i)
			{
			return (string)stringList[i];
			}

		/**
		 * Writes out the shared string table
		 *
		 * @param outputFile the binary output file
		 * @exception IOException
		 */
		public void write(File outputFile)
			{
			// Thanks to Guenther for contributing the ExtSST implementation portion
			// of this method
			int charsLeft = 0;
			string curString = null;
			SSTRecord sst = new SSTRecord(totalOccurrences, stringList.Count);
			ExtendedSSTRecord extsst = new ExtendedSSTRecord(stringList.Count);
			int bucketSize = extsst.getNumberOfStringsPerBucket();

			int stringIndex = 0;
			// CML: this one is nasty....
			IEnumerator iterator = stringList.GetEnumerator();
			while (iterator.MoveNext() && charsLeft == 0)
				{
				curString = (string)iterator.Current;
				// offset + header bytes
				int relativePosition = sst.getOffset() + 4;
				charsLeft = sst.add(curString);
				if ((stringIndex % bucketSize) == 0)
					extsst.addString(outputFile.getPos(), relativePosition);
				stringIndex++;
				}
			outputFile.write(sst);

			if (charsLeft != 0 || iterator.MoveNext())
				{
				// Add the remainder of the string to the continue record
				SSTContinueRecord cont = createContinueRecord(curString,charsLeft,outputFile);

				// Carry on looping through the array until all the strings are done
				do
					{
					curString = (string)iterator.Current;
					int relativePosition = cont.getOffset() + 4;
					charsLeft = cont.add(curString);
					if ((stringIndex % bucketSize) == 0)
						extsst.addString(outputFile.getPos(), relativePosition);
					stringIndex++;

					if (charsLeft != 0)
						{
						outputFile.write(cont);
						cont = createContinueRecord(curString, charsLeft, outputFile);
						}
					}
				while (iterator.MoveNext());

				outputFile.write(cont);
				}

			outputFile.write(extsst);
			}

		/**
		 * Creates and returns a continue record using the left over bits and
		 * pieces
		 */
		private SSTContinueRecord createContinueRecord
		  (string curString, int charsLeft, File outputFile)
			{
			// Set up the remainder of the string in the continue record
			SSTContinueRecord cont = null;
			while (charsLeft != 0)
				{
				cont = new SSTContinueRecord();

				if (charsLeft == curString.Length || curString.Length == 0)
					{
					charsLeft = cont.setFirstString(curString, true);
					}
				else
					{
					charsLeft = cont.setFirstString
					  (curString.Substring(curString.Length - charsLeft), false);
					}

				if (charsLeft != 0)
					{
					outputFile.write(cont);
					cont = new SSTContinueRecord();
					}
				}

			return cont;
			}
		}
	}	
