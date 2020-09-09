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


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * An enumeration class which contains the  biff types
	 */
	public sealed class Type
		{
		/**
		 * The biff value for this type
		 */
		public int value;
		/**
		 * An array of all types
		 */
		private static Type[] types = new Type[0];

		/**
		 * Constructor
		 * Sets the biff value and adds this type to the array of all types
		 *
		 * @param v the biff code for the type
		 */
		private Type(int v)
			{
			value = v;

			// Add to the list of available types
			Type[] newTypes = new Type[types.Length + 1];
			System.Array.Copy(types,0,newTypes,0,types.Length);
			newTypes[types.Length] = this;
			types = newTypes;
			}

		public sealed class ArbitraryType
			{
			};

		private static ArbitraryType arbitrary = new ArbitraryType();

		/**
		 * Constructor used for the creation of arbitrary types
		 */
		private Type(int v,ArbitraryType arb)
			{
			value = v;
			}

		/**
		 * Standard hash code method
		 * @return the hash code
		 */
		public override int GetHashCode()
			{
			return value;
			}

		/**
		 * Standard equals method
		 * @param o the object to compare
		 * @return TRUE if the objects are equal, FALSE otherwise
		 */
		public override bool Equals(object o)
			{
			if (o == this)
				return true;

			if (!(o is Type))
				return false;

			Type t = (Type)o;

			return value == t.value;
			}

		/**
		 * Gets the type object from its integer value
		 * @param v the internal code
		 * @return the type
		 */
		public static Type getType(int v)
			{
			for (int i = 0; i < types.Length; i++)
				{
				if (types[i].value == v)
					{
					return types[i];
					}
				}

			return UNKNOWN;
			}

		/**
		 * Used to create an arbitrary record type.  This method is only
		 * used during bespoke debugging process.  The creation of an
		 * arbitrary type does not add it to the static list of known types
		 */
		public static Type createType(int v)
			{
			return new Type(v,arbitrary);
			}

		/**
		 */
		public static readonly Type BOF = new Type(0x809);
		/**
		 */
		public static readonly Type EOF = new Type(0x0a);
		/**
		 */
		public static readonly Type BOUNDSHEET = new Type(0x85);
		/**
		 */
		public static readonly Type SUPBOOK = new Type(0x1ae);
		/**
		 */
		public static readonly Type EXTERNSHEET = new Type(0x17);
		/**
		 */
		public static readonly Type DIMENSION = new Type(0x200);
		/**
		 */
		public static readonly Type BLANK = new Type(0x201);
		/**
		 */
		public static readonly Type MULBLANK = new Type(0xbe);
		/**
		 */
		public static readonly Type ROW = new Type(0x208);
		/**
		 */
		public static readonly Type NOTE = new Type(0x1c);
		/**
		 */
		public static readonly Type TXO = new Type(0x1b6);
		/**
		 */
		public static readonly Type RK = new Type(0x7e);
		/**
		 */
		public static readonly Type RK2 = new Type(0x27e);
		/**
		 */
		public static readonly Type MULRK = new Type(0xbd);
		/**
		 */
		public static readonly Type INDEX = new Type(0x20b);
		/**
		 */
		public static readonly Type DBCELL = new Type(0xd7);
		/**
		 */
		public static readonly Type SST = new Type(0xfc);
		/**
		 */
		public static readonly Type COLINFO = new Type(0x7d);
		/**
		 */
		public static readonly Type EXTSST = new Type(0xff);
		/**
		 */
		public static readonly Type CONTINUE = new Type(0x3c);
		/**
		 */
		public static readonly Type LABEL = new Type(0x204);
		/**
		 */
		public static readonly Type RSTRING = new Type(0xd6);
		/**
		 */
		public static readonly Type LABELSST = new Type(0xfd);
		/**
		 */
		public static readonly Type NUMBER = new Type(0x203);
		/**
		 */
		public static readonly Type NAME = new Type(0x18);
		/**
		 */
		public static readonly Type TABID = new Type(0x13d);
		/**
		 */
		public static readonly Type ARRAY = new Type(0x221);
		/**
		 */
		public static readonly Type STRING = new Type(0x207);
		/**
		 */
		public static readonly Type FORMULA = new Type(0x406);
		/**
		 */
		public static readonly Type FORMULA2 = new Type(0x6);
		/**
		 */
		public static readonly Type SHAREDFORMULA = new Type(0x4bc);
		/**
		 */
		public static readonly Type FORMAT = new Type(0x41e);
		/**
		 */
		public static readonly Type XF = new Type(0xe0);
		/**
		 */
		public static readonly Type BOOLERR = new Type(0x205);
		/**
		 */
		public static readonly Type INTERFACEHDR = new Type(0xe1);
		/**
		 */
		public static readonly Type SAVERECALC = new Type(0x5f);
		/**
		 */
		public static readonly Type INTERFACEEND = new Type(0xe2);
		/**
		 */
		public static readonly Type XCT = new Type(0x59);
		/**
		 */
		public static readonly Type CRN = new Type(0x5a);
		/**
		 */
		public static readonly Type DEFCOLWIDTH = new Type(0x55);
		/**
		 */
		public static readonly Type DEFAULTROWHEIGHT = new Type(0x225);
		/**
		 */
		public static readonly Type WRITEACCESS = new Type(0x5c);
		/**
		 */
		public static readonly Type WSBOOL = new Type(0x81);
		/**
		 */
		public static readonly Type CODEPAGE = new Type(0x42);
		/**
		 */
		public static readonly Type DSF = new Type(0x161);
		/**
		 */
		public static readonly Type FNGROUPCOUNT = new Type(0x9c);
		/**
		 */
		public static readonly Type FILTERMODE = new Type(0x9b);
		/**
		 */
		public static readonly Type AUTOFILTERINFO = new Type(0x9d);
		/**
		 */
		public static readonly Type AUTOFILTER = new Type(0x9e);
		/**
		 */
		public static readonly Type COUNTRY = new Type(0x8c);
		/**
		 */
		public static readonly Type PROTECT = new Type(0x12);
		/**
		 */
		public static readonly Type SCENPROTECT = new Type(0xdd);
		/**
		 */
		public static readonly Type OBJPROTECT = new Type(0x63);
		/**
		 */
		public static readonly Type PRINTHEADERS = new Type(0x2a);
		/**
		 */
		public static readonly Type HEADER = new Type(0x14);
		/**
		 */
		public static readonly Type FOOTER = new Type(0x15);
		/**
		 */
		public static readonly Type HCENTER = new Type(0x83);
		/**
		 */
		public static readonly Type VCENTER = new Type(0x84);
		/**
		 */
		public static readonly Type FILEPASS = new Type(0x2f);
		/**
		 */
		public static readonly Type SETUP = new Type(0xa1);
		/**
		 */
		public static readonly Type PRINTGRIDLINES = new Type(0x2b);
		/**
		 */
		public static readonly Type GRIDSET = new Type(0x82);
		/**
		 */
		public static readonly Type GUTS = new Type(0x80);
		/**
		 */
		public static readonly Type WINDOWPROTECT = new Type(0x19);
		/**
		 */
		public static readonly Type PROT4REV = new Type(0x1af);
		/**
		 */
		public static readonly Type PROT4REVPASS = new Type(0x1bc);
		/**
		 */
		public static readonly Type PASSWORD = new Type(0x13);
		/**
		 */
		public static readonly Type REFRESHALL = new Type(0x1b7);
		/**
		 */
		public static readonly Type WINDOW1 = new Type(0x3d);
		/**
		 */
		public static readonly Type WINDOW2 = new Type(0x23e);
		/**
		 */
		public static readonly Type BACKUP = new Type(0x40);
		/**
		 */
		public static readonly Type HIDEOBJ = new Type(0x8d);
		/**
		 */
		public static readonly Type NINETEENFOUR = new Type(0x22);
		/**
		 */
		public static readonly Type PRECISION = new Type(0xe);
		/**
		 */
		public static readonly Type BOOKBOOL = new Type(0xda);
		/**
		 */
		public static readonly Type FONT = new Type(0x31);
		/**
		 */
		public static readonly Type MMS = new Type(0xc1);
		/**
		 */
		public static readonly Type CALCMODE = new Type(0x0d);
		/**
		 */
		public static readonly Type CALCCOUNT = new Type(0x0c);
		/**
		 */
		public static readonly Type REFMODE = new Type(0x0f);
		/**
		 */
		public static readonly Type TEMPLATE = new Type(0x60);
		/**
		 */
		public static readonly Type OBJPROJ = new Type(0xd3);
		/**
		 */
		public static readonly Type DELTA = new Type(0x10);
		/**
		 */
		public static readonly Type MERGEDCELLS = new Type(0xe5);
		/**
		 */
		public static readonly Type ITERATION = new Type(0x11);
		/**
		 */
		public static readonly Type STYLE = new Type(0x293);
		/**
		 */
		public static readonly Type USESELFS = new Type(0x160);
		/**
		 */
		public static readonly Type VERTICALPAGEBREAKS = new Type(0x1a);
		/**
		 */
		public static readonly Type HORIZONTALPAGEBREAKS = new Type(0x1b);
		/**
		 */
		public static readonly Type SELECTION = new Type(0x1d);
		/**
		 */
		public static readonly Type HLINK = new Type(0x1b8);
		/**
		 */
		public static readonly Type OBJ = new Type(0x5d);
		/**
		 */
		public static readonly Type MSODRAWING = new Type(0xec);
		/**
		 */
		public static readonly Type MSODRAWINGGROUP = new Type(0xeb);
		/**
		 */
		public static readonly Type LEFTMARGIN = new Type(0x26);
		/**
		 */
		public static readonly Type RIGHTMARGIN = new Type(0x27);
		/**
		 */
		public static readonly Type TOPMARGIN = new Type(0x28);
		/**
		 */
		public static readonly Type BOTTOMMARGIN = new Type(0x29);
		/**
		 */
		public static readonly Type EXTERNNAME = new Type(0x23);
		/**
		 */
		public static readonly Type PALETTE = new Type(0x92);
		/**
		 */
		public static readonly Type PLS = new Type(0x4d);
		/**
		 */
		public static readonly Type SCL = new Type(0xa0);
		/**
		 */
		public static readonly Type PANE = new Type(0x41);
		/**
		 */
		public static readonly Type WEIRD1 = new Type(0xef);
		/**
		 */
		public static readonly Type SORT = new Type(0x90);
		/**
		 */
		public static readonly Type CONDFMT = new Type(0x1b0);
		/**
		 */
		public static readonly Type CF = new Type(0x1b1);
		/**
		 */
		public static readonly Type DV = new Type(0x1be);
		/**
		 */
		public static readonly Type DVAL = new Type(0x1b2);
		/**
		 */
		public static readonly Type BUTTONPROPERTYSET = new Type(0x1ba);
		/**
		 *
		 */
		public static readonly Type EXCEL9FILE = new Type(0x1c0);

		// Chart types
		/**
		 */
		public static readonly Type FONTX = new Type(0x1026);
		/**
		 */
		public static readonly Type IFMT = new Type(0x104e);
		/**
		 */
		public static readonly Type FBI = new Type(0x1060);
		/**
		 */
		public static readonly Type ALRUNS = new Type(0x1050);
		/**
		 */
		public static readonly Type SERIES = new Type(0x1003);
		/**
		 */
		public static readonly Type SERIESLIST = new Type(0x1016);
		/**
		 */
		public static readonly Type SBASEREF = new Type(0x1048);
		/**
		 */
		public static readonly Type UNKNOWN = new Type(0xffff);

		// Pivot stuff
		/**
		 */
		// public static readonly Type R = new Type(0xffff);

		// Unknown types
		public static readonly Type U1C0 = new Type(0x1c0);
		public static readonly Type U1C1 = new Type(0x1c1);

		}
	}









