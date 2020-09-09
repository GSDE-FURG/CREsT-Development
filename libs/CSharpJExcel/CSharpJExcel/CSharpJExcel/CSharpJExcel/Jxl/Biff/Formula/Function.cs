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


namespace CSharpJExcel.Jxl.Biff.Formula
	{
	/**
	 * An enumeration detailing the Excel function codes
	 */
	public sealed class Function
		{
		/**
		 * The logger
		 */
		//private static Logger logger = Logger.getLogger(Function.class);

		/**
		 * The code which applies to this function
		 */
		private int code;

		/**
		 * The property name of this function
		 */
		private string name;

		/**
		 * The number of args this function expects
		 */
		private int numArgs;


		/**
		 * All available functions.  This attribute is package protected in order
		 * to enable the FunctionNames to initialize
		 */
		private static Function[] functions = new Function[0];

		/**
		 * Constructor
		 * Sets the token value and adds this token to the array of all token
		 *
		 * @param v the biff code for the token
		 * @param s the string
		 * @param a the number of arguments
		 */
		private Function(int v,string s,int a)
			{
			code = v;
			name = s;
			numArgs = a;

			// Grow the array
			Function[] newarray = new Function[functions.Length + 1];
			System.Array.Copy(functions,0,newarray,0,functions.Length);
			newarray[functions.Length] = this;
			functions = newarray;
			}

		/**
		 * Standard hash code method
		 *
		 * @return the hash code
		 */
		public override int GetHashCode()
			{
			return code;
			}

		/**
		 * Gets the function code - used when generating token data
		 *
		 * @return the code
		 */
		public int getCode()
			{
			return code;
			}

		/**
		 * Gets the property name. Used by the FunctionNames object when initializing
		 * the locale specific names
		 *
		 * @return the property name for this function
		 */
		public string getPropertyName()
			{
			return name;
			}

		/**
		 * Gets the function name
		 * @param ws the workbook settings
		 * @return the function name
		 */
		public string getName(WorkbookSettings ws)
			{
			FunctionNames fn = ws.getFunctionNames();
			return fn.getName(this);
			}

		/**
		 * Gets the number of arguments for this function
		 *
		 * @return the number of arguments
		 */
		public int getNumArgs()
			{
			return numArgs;
			}

		/**
		 * Gets the type object from its integer value
		 *
		 * @param v the function value
		 * @return the function
		 */
		public static Function getFunction(int v)
			{
			Function f = null;

			for (int i = 0; i < functions.Length; i++)
				{
				if (functions[i].code == v)
					{
					f = functions[i];
					break;
					}
				}

			return f != null ? f : UNKNOWN;
			}

		/**
		 * Gets the type object from its string value.  Used when parsing strings
		 *
		 * @param v the function name
		 * @param ws the workbook settings
		 * @return the function
		 */
		public static Function getFunction(string v,WorkbookSettings ws)
			{
			FunctionNames fn = ws.getFunctionNames();
			Function f = fn.getFunction(v);
			return f != null ? f : UNKNOWN;
			}

		/**
		 * Accessor for all the functions, used by the internationalization
		 * work around
		 *
		 * @return all the functions
		 */
		public static Function[] getFunctions()
			{
			return functions;
			}

		// The functions
		public static readonly Function COUNT =
		  new Function(0x0,"count",0xff);
		public static readonly Function ATTRIBUTE = new Function(0x1,string.Empty,0xff);
		public static readonly Function ISNA =
		  new Function(0x2,"isna",1);
		public static readonly Function ISERROR =
		  new Function(0x3,"iserror",1);
		public static readonly Function SUM =
		  new Function(0x4,"sum",0xff);
		public static readonly Function AVERAGE =
		  new Function(0x5,"average",0xff);
		public static readonly Function MIN =
		  new Function(0x6,"min",0xff);
		public static readonly Function MAX =
		  new Function(0x7,"max",0xff);
		public static readonly Function ROW =
		  new Function(0x8,"row",0xff);
		public static readonly Function COLUMN =
		  new Function(0x9,"column",0xff);
		public static readonly Function NA =
		  new Function(0xa,"na",0);
		public static readonly Function NPV =
		  new Function(0xb,"npv",0xff);
		public static readonly Function STDEV =
		  new Function(0xc,"stdev",0xff);
		public static readonly Function DOLLAR =
		  new Function(0xd,"dollar",2);
		public static readonly Function FIXED =
		  new Function(0xe,"fixed",0xff);
		public static readonly Function SIN =
		  new Function(0xf,"sin",1);
		public static readonly Function COS =
		  new Function(0x10,"cos",1);
		public static readonly Function TAN =
		  new Function(0x11,"tan",1);
		public static readonly Function ATAN =
		  new Function(0x12,"atan",1);
		public static readonly Function PI =
		  new Function(0x13,"pi",0);
		public static readonly Function SQRT =
		  new Function(0x14,"sqrt",1);
		public static readonly Function EXP =
		  new Function(0x15,"exp",1);
		public static readonly Function LN =
		  new Function(0x16,"ln",1);
		public static readonly Function LOG10 =
		  new Function(0x17,"log10",1);
		public static readonly Function ABS =
		  new Function(0x18,"abs",1);
		public static readonly Function INT =
		  new Function(0x19,"int",1);
		public static readonly Function SIGN =
		  new Function(0x1a,"sign",1);
		public static readonly Function ROUND =
		  new Function(0x1b,"round",2);
		public static readonly Function LOOKUP =
		  new Function(0x1c,"lookup",2);
		public static readonly Function INDEX =
		  new Function(0x1d,"index",3);
		public static readonly Function REPT = new Function(0x1e,"rept",2);
		public static readonly Function MID =
		  new Function(0x1f,"mid",3);
		public static readonly Function LEN =
		  new Function(0x20,"len",1);
		public static readonly Function VALUE =
		  new Function(0x21,"value",1);
		public static readonly Function TRUE =
		  new Function(0x22,"true",0);
		public static readonly Function FALSE =
		  new Function(0x23,"false",0);
		public static readonly Function AND =
		  new Function(0x24,"and",0xff);
		public static readonly Function OR =
		  new Function(0x25,"or",0xff);
		public static readonly Function NOT =
		  new Function(0x26,"not",1);
		public static readonly Function MOD =
		  new Function(0x27,"mod",2);
		public static readonly Function DCOUNT =
		  new Function(0x28,"dcount",3);
		public static readonly Function DSUM =
		  new Function(0x29,"dsum",3);
		public static readonly Function DAVERAGE =
		  new Function(0x2a,"daverage",3);
		public static readonly Function DMIN =
		  new Function(0x2b,"dmin",3);
		public static readonly Function DMAX =
		  new Function(0x2c,"dmax",3);
		public static readonly Function DSTDEV =
		  new Function(0x2d,"dstdev",3);
		public static readonly Function VAR =
		  new Function(0x2e,"var",0xff);
		public static readonly Function DVAR =
		  new Function(0x2f,"dvar",3);
		public static readonly Function TEXT =
		  new Function(0x30,"text",2);
		public static readonly Function LINEST =
		  new Function(0x31,"linest",0xff);
		public static readonly Function TREND =
		  new Function(0x32,"trend",0xff);
		public static readonly Function LOGEST =
		  new Function(0x33,"logest",0xff);
		public static readonly Function GROWTH =
		  new Function(0x34,"growth",0xff);
		//public static readonly Function GOTO =  new Function(0x35, "GOTO",);
		//public static readonly Function HALT =  new Function(0x36, "HALT",);
		public static readonly Function PV =
		  new Function(0x38,"pv",0xff);
		public static readonly Function FV =
		  new Function(0x39,"fv",0xff);
		public static readonly Function NPER =
		  new Function(0x3a,"nper",0xff);
		public static readonly Function PMT =
		  new Function(0x3b,"pmt",0xff);
		public static readonly Function RATE =
		  new Function(0x3c,"rate",0xff);
		//public static readonly Function MIRR =  new Function(0x3d, "MIRR",);
		//public static readonly Function IRR =  new Function(0x3e, "IRR",);
		public static readonly Function RAND =
		  new Function(0x3f,"rand",0);
		public static readonly Function MATCH =
		  new Function(0x40,"match",3);
		public static readonly Function DATE =
		  new Function(0x41,"date",3);
		public static readonly Function TIME =
		  new Function(0x42,"time",3);
		public static readonly Function DAY =
		  new Function(0x43,"day",1);
		public static readonly Function MONTH =
		  new Function(0x44,"month",1);
		public static readonly Function YEAR =
		  new Function(0x45,"year",1);
		public static readonly Function WEEKDAY =
		  new Function(0x46,"weekday",2);
		public static readonly Function HOUR =
		  new Function(0x47,"hour",1);
		public static readonly Function MINUTE =
		  new Function(0x48,"minute",1);
		public static readonly Function SECOND =
		  new Function(0x49,"second",1);
		public static readonly Function NOW =
		  new Function(0x4a,"now",0);
		public static readonly Function AREAS =
		  new Function(0x4b,"areas",0xff);
		public static readonly Function ROWS =
		  new Function(0x4c,"rows",1);
		public static readonly Function COLUMNS =
		  new Function(0x4d,"columns",0xff);
		public static readonly Function OFFSET =
		  new Function(0x4e,"offset",0xff);
		//public static readonly Function ABSREF =  new Function(0x4f, "ABSREF",);
		//public static readonly Function RELREF =  new Function(0x50, "RELREF",);
		//public static readonly Function ARGUMENT =  new Function(0x51,"ARGUMENT",);
		public static readonly Function SEARCH = new Function(0x52,"search",0xff);
		public static readonly Function TRANSPOSE =
		  new Function(0x53,"transpose",0xff);
		public static readonly Function ERROR =
		  new Function(0x54,"error",1);
		//public static readonly Function STEP =  new Function(0x55, "STEP",);
		public static readonly Function TYPE =
		  new Function(0x56,"type",1);
		//public static readonly Function ECHO =  new Function(0x57, "ECHO",);
		//public static readonly Function SETNAME =  new Function(0x58, "SETNAME",);
		//public static readonly Function CALLER =  new Function(0x59, "CALLER",);
		//public static readonly Function DEREF =  new Function(0x5a, "DEREF",);
		//public static readonly Function WINDOWS =  new Function(0x5b, "WINDOWS",);
		//public static readonly Function SERIES =  new Function(0x5c, "SERIES",);
		//public static readonly Function DOCUMENTS =  new Function(0x5d,"DOCUMENTS",);
		//public static readonly Function ACTIVECELL =  new Function(0x5e,"ACTIVECELL",);
		//public static readonly Function SELECTION =  new Function(0x5f,"SELECTION",);
		//public static readonly Function RESULT =  new Function(0x60, "RESULT",);
		public static readonly Function ATAN2 =
		  new Function(0x61,"atan2",1);
		public static readonly Function ASIN =
		  new Function(0x62,"asin",1);
		public static readonly Function ACOS =
		  new Function(0x63,"acos",1);
		public static readonly Function CHOOSE =
		  new Function(0x64,"choose",0xff);
		public static readonly Function HLOOKUP =
		  new Function(0x65,"hlookup",0xff);
		public static readonly Function VLOOKUP =
		  new Function(0x66,"vlookup",0xff);
		//public static readonly Function LINKS =  new Function(0x67, "LINKS",);
		//public static readonly Function INPUT =  new Function(0x68, "INPUT",);
		public static readonly Function ISREF =
		  new Function(0x69,"isref",1);
		//public static readonly Function GETFORMULA =  new Function(0x6a,"GETFORMULA",);
		//public static readonly Function GETNAME =  new Function(0x6b, "GETNAME",);
		//public static readonly Function SETVALUE =  new Function(0x6c,"SETVALUE",);
		public static readonly Function LOG =
		  new Function(0x6d,"log",0xff);
		//public static readonly Function EXEC =  new Function(0x6e, "EXEC",);
		public static readonly Function CHAR =
		  new Function(0x6f,"char",1);
		public static readonly Function LOWER =
		  new Function(0x70,"lower",1);
		public static readonly Function UPPER =
		  new Function(0x71,"upper",1);
		public static readonly Function PROPER =
		  new Function(0x72,"proper",1);
		public static readonly Function LEFT =
		  new Function(0x73,"left",0xff);
		public static readonly Function RIGHT =
		  new Function(0x74,"right",0xff);
		public static readonly Function EXACT =
		  new Function(0x75,"exact",2);
		public static readonly Function TRIM =
		  new Function(0x76,"trim",1);
		public static readonly Function REPLACE =
		  new Function(0x77,"replace",4);
		public static readonly Function SUBSTITUTE =
		  new Function(0x78,"substitute",0xff);
		public static readonly Function CODE =
		  new Function(0x79,"code",1);
		//public static readonly Function NAMES =  new Function(0x7a, "NAMES",);
		//public static readonly Function DIRECTORY =  new Function(0x7b,"DIRECTORY",);
		public static readonly Function FIND =
		  new Function(0x7c,"find",0xff);
		public static readonly Function CELL =
		  new Function(0x7d,"cell",2);
		public static readonly Function ISERR =
		  new Function(0x7e,"iserr",1);
		public static readonly Function ISTEXT =
		  new Function(0x7f,"istext",1);
		public static readonly Function ISNUMBER =
		  new Function(0x80,"isnumber",1);
		public static readonly Function ISBLANK =
		  new Function(0x81,"isblank",1);
		public static readonly Function T =
		  new Function(0x82,"t",1);
		public static readonly Function N =
		  new Function(0x83,"n",1);
		//public static readonly Function FOPEN =  new Function(0x84, "FOPEN",);
		//public static readonly Function FCLOSE =  new Function(0x85, "FCLOSE",);
		//public static readonly Function FSIZE =  new Function(0x86, "FSIZE",);
		//public static readonly Function FREADLN =  new Function(0x87, "FREADLN",);
		//public static readonly Function FREAD =  new Function(0x88, "FREAD",);
		//public static readonly Function FWRITELN =  new Function(0x89,"FWRITELN",);
		//public static readonly Function FWRITE =  new Function(0x8a, "FWRITE",);
		//public static readonly Function FPOS =  new Function(0x8b, "FPOS",);
		public static readonly Function DATEVALUE =
		  new Function(0x8c,"datevalue",1);
		public static readonly Function TIMEVALUE =
		  new Function(0x8d,"timevalue",1);
		public static readonly Function SLN =
		  new Function(0x8e,"sln",3);
		public static readonly Function SYD =
		  new Function(0x8f,"syd",3);
		public static readonly Function DDB =
		  new Function(0x90,"ddb",0xff);
		//public static readonly Function GETDEF =  new Function(0x91, "GETDEF",);
		//public static readonly Function REFTEXT =  new Function(0x92, "REFTEXT",);
		//public static readonly Function TEXTREF =  new Function(0x93, "TEXTREF",);
		public static readonly Function INDIRECT =
		  new Function(0x94,"indirect",0xff);
		//public static readonly Function REGISTER =  new Function(0x95,"REGISTER",);
		//public static readonly Function CALL =  new Function(0x96, "CALL",);
		//public static readonly Function ADDBAR =  new Function(0x97, "ADDBAR",);
		//public static readonly Function ADDMENU =  new Function(0x98, "ADDMENU",);
		//public static readonly Function ADDCOMMAND =
		// new Function(0x99,"ADDCOMMAND",);
		//public static readonly Function ENABLECOMMAND =
		// new Function(0x9a,"ENABLECOMMAND",);
		//public static readonly Function CHECKCOMMAND =
		// new Function(0x9b,"CHECKCOMMAND",);
		//public static readonly Function RENAMECOMMAND =
		// new Function(0x9c,"RENAMECOMMAND",);
		//public static readonly Function SHOWBAR =  new Function(0x9d, "SHOWBAR",);
		//public static readonly Function DELETEMENU =
		//  new Function(0x9e,"DELETEMENU",);
		//public static readonly Function DELETECOMMAND =
		//  new Function(0x9f,"DELETECOMMAND",);
		//public static readonly Function GETCHARTITEM =
		//  new Function(0xa0,"GETCHARTITEM",);
		//public static readonly Function DIALOGBOX =  new Function(0xa1,"DIALOGBOX",);
		public static readonly Function CLEAN =
		  new Function(0xa2,"clean",1);
		public static readonly Function MDETERM =
		  new Function(0xa3,"mdeterm",0xff);
		public static readonly Function MINVERSE =
		  new Function(0xa4,"minverse",0xff);
		public static readonly Function MMULT =
		  new Function(0xa5,"mmult",0xff);
		//public static readonly Function FILES =  new Function(0xa6, "FILES",

		public static readonly Function IPMT =
		  new Function(0xa7,"ipmt",0xff);
		public static readonly Function PPMT =
		  new Function(0xa8,"ppmt",0xff);
		public static readonly Function COUNTA =
		  new Function(0xa9,"counta",0xff);
		public static readonly Function PRODUCT =
		  new Function(0xb7,"product",0xff);
		public static readonly Function FACT =
		  new Function(0xb8,"fact",1);
		//public static readonly Function GETCELL =  new Function(0xb9, "GETCELL",);
		//public static readonly Function GETWORKSPACE =
		//  new Function(0xba,"GETWORKSPACE",);
		//public static readonly Function GETWINDOW =  new Function(0xbb,"GETWINDOW",);
		//public static readonly Function GETDOCUMENT =
		//  new Function(0xbc,"GETDOCUMENT",);
		public static readonly Function DPRODUCT =
		  new Function(0xbd,"dproduct",3);
		public static readonly Function ISNONTEXT =
		  new Function(0xbe,"isnontext",1);
		//public static readonly Function GETNOTE =  new Function(0xbf, "GETNOTE",);
		//public static readonly Function NOTE =  new Function(0xc0, "NOTE",);
		public static readonly Function STDEVP =
		  new Function(0xc1,"stdevp",0xff);
		public static readonly Function VARP =
		  new Function(0xc2,"varp",0xff);
		public static readonly Function DSTDEVP =
		  new Function(0xc3,"dstdevp",0xff);
		public static readonly Function DVARP =
		  new Function(0xc4,"dvarp",0xff);
		public static readonly Function TRUNC =
		  new Function(0xc5,"trunc",0xff);
		public static readonly Function ISLOGICAL =
		  new Function(0xc6,"islogical",1);
		public static readonly Function DCOUNTA =
		  new Function(0xc7,"dcounta",0xff);
		public static readonly Function FINDB =
		  new Function(0xcd,"findb",0xff);
		public static readonly Function SEARCHB =
		  new Function(0xce,"searchb",3);
		public static readonly Function REPLACEB =
		  new Function(0xcf,"replaceb",4);
		public static readonly Function LEFTB =
		  new Function(0xd0,"leftb",0xff);
		public static readonly Function RIGHTB =
		  new Function(0xd1,"rightb",0xff);
		public static readonly Function MIDB =
		  new Function(0xd2,"midb",3);
		public static readonly Function LENB =
		  new Function(0xd3,"lenb",1);
		public static readonly Function ROUNDUP =
		  new Function(0xd4,"roundup",2);
		public static readonly Function ROUNDDOWN =
		  new Function(0xd5,"rounddown",2);
		public static readonly Function RANK =
		  new Function(0xd8,"rank",0xff);
		public static readonly Function ADDRESS =
		  new Function(0xdb,"address",0xff);
		public static readonly Function AYS360 =
		  new Function(0xdc,"days360",0xff);
		public static readonly Function ODAY =
		  new Function(0xdd,"today",0);
		public static readonly Function VDB =
		  new Function(0xde,"vdb",0xff);
		public static readonly Function MEDIAN =
		  new Function(0xe3,"median",0xff);
		public static readonly Function SUMPRODUCT =
		  new Function(0xe4,"sumproduct",0xff);
		public static readonly Function SINH =
		  new Function(0xe5,"sinh",1);
		public static readonly Function COSH =
		  new Function(0xe6,"cosh",1);
		public static readonly Function TANH =
		  new Function(0xe7,"tanh",1);
		public static readonly Function ASINH =
		  new Function(0xe8,"asinh",1);
		public static readonly Function ACOSH =
		  new Function(0xe9,"acosh",1);
		public static readonly Function ATANH =
		  new Function(0xea,"atanh",1);
		public static readonly Function INFO =
		  new Function(0xf4,"info",1);
		public static readonly Function AVEDEV =
		  new Function(0x10d,"avedev",0XFF);
		public static readonly Function BETADIST =
		  new Function(0x10e,"betadist",0XFF);
		public static readonly Function GAMMALN =
		  new Function(0x10f,"gammaln",1);
		public static readonly Function BETAINV =
		  new Function(0x110,"betainv",0XFF);
		public static readonly Function BINOMDIST =
		  new Function(0x111,"binomdist",4);
		public static readonly Function CHIDIST =
		  new Function(0x112,"chidist",2);
		public static readonly Function CHIINV =
		  new Function(0x113,"chiinv",2);
		public static readonly Function COMBIN =
		  new Function(0x114,"combin",2);
		public static readonly Function CONFIDENCE =
		  new Function(0x115,"confidence",3);
		public static readonly Function CRITBINOM =
		  new Function(0x116,"critbinom",3);
		public static readonly Function EVEN =
		  new Function(0x117,"even",1);
		public static readonly Function EXPONDIST =
		  new Function(0x118,"expondist",3);
		public static readonly Function FDIST =
		  new Function(0x119,"fdist",3);
		public static readonly Function FINV =
		  new Function(0x11a,"finv",3);
		public static readonly Function FISHER =
		  new Function(0x11b,"fisher",1);
		public static readonly Function FISHERINV =
		  new Function(0x11c,"fisherinv",1);
		public static readonly Function FLOOR =
		  new Function(0x11d,"floor",2);
		public static readonly Function GAMMADIST =
		  new Function(0x11e,"gammadist",4);
		public static readonly Function GAMMAINV =
		  new Function(0x11f,"gammainv",3);
		public static readonly Function CEILING =
		  new Function(0x120,"ceiling",2);
		public static readonly Function HYPGEOMDIST =
		  new Function(0x121,"hypgeomdist",4);
		public static readonly Function LOGNORMDIST =
		  new Function(0x122,"lognormdist",3);
		public static readonly Function LOGINV =
		  new Function(0x123,"loginv",3);
		public static readonly Function NEGBINOMDIST =
		  new Function(0x124,"negbinomdist",3);
		public static readonly Function NORMDIST =
		  new Function(0x125,"normdist",4);
		public static readonly Function NORMSDIST =
		  new Function(0x126,"normsdist",1);
		public static readonly Function NORMINV =
		  new Function(0x127,"norminv",3);
		public static readonly Function NORMSINV =
		  new Function(0x128,"normsinv",1);
		public static readonly Function STANDARDIZE =
		  new Function(0x129,"standardize",3);
		public static readonly Function ODD =
		  new Function(0x12a,"odd",1);
		public static readonly Function PERMUT =
		  new Function(0x12b,"permut",2);
		public static readonly Function POISSON =
		  new Function(0x12c,"poisson",3);
		public static readonly Function TDIST =
		  new Function(0x12d,"tdist",3);
		public static readonly Function WEIBULL =
		  new Function(0x12e,"weibull",4);
		public static readonly Function SUMXMY2 =
		  new Function(303,"sumxmy2",0xff);
		public static readonly Function SUMX2MY2 =
		  new Function(304,"sumx2my2",0xff);
		public static readonly Function SUMX2PY2 =
		  new Function(305,"sumx2py2",0xff);
		public static readonly Function CHITEST =
		  new Function(0x132,"chitest",0xff);
		public static readonly Function CORREL =
		  new Function(0x133,"correl",0xff);
		public static readonly Function COVAR =
		  new Function(0x134,"covar",0xff);
		public static readonly Function FORECAST =
		  new Function(0x135,"forecast",0xff);
		public static readonly Function FTEST =
		  new Function(0x136,"ftest",0xff);
		public static readonly Function INTERCEPT =
		  new Function(0x137,"intercept",0xff);
		public static readonly Function PEARSON =
		  new Function(0x138,"pearson",0xff);
		public static readonly Function RSQ =
		  new Function(0x139,"rsq",0xff);
		public static readonly Function STEYX =
		  new Function(0x13a,"steyx",0xff);
		public static readonly Function SLOPE =
		  new Function(0x13b,"slope",2);
		public static readonly Function TTEST =
		  new Function(0x13c,"ttest",0xff);
		public static readonly Function PROB =
		  new Function(0x13d,"prob",0xff);
		public static readonly Function DEVSQ =
		  new Function(0x13e,"devsq",0xff);
		public static readonly Function GEOMEAN =
		  new Function(0x13f,"geomean",0xff);
		public static readonly Function HARMEAN =
		  new Function(0x140,"harmean",0xff);
		public static readonly Function SUMSQ =
		  new Function(0x141,"sumsq",0xff);
		public static readonly Function KURT =
		  new Function(0x142,"kurt",0xff);
		public static readonly Function SKEW =
		  new Function(0x143,"skew",0xff);
		public static readonly Function ZTEST =
		  new Function(0x144,"ztest",0xff);
		public static readonly Function LARGE =
		  new Function(0x145,"large",0xff);
		public static readonly Function SMALL =
		  new Function(0x146,"small",0xff);
		public static readonly Function QUARTILE =
		  new Function(0x147,"quartile",0xff);
		public static readonly Function PERCENTILE =
		  new Function(0x148,"percentile",0xff);
		public static readonly Function PERCENTRANK =
		  new Function(0x149,"percentrank",0xff);
		public static readonly Function MODE =
		  new Function(0x14a,"mode",0xff);
		public static readonly Function TRIMMEAN =
		  new Function(0x14b,"trimmean",0xff);
		public static readonly Function TINV =
		  new Function(0x14c,"tinv",2);
		public static readonly Function CONCATENATE =
		  new Function(0x150,"concatenate",0xff);
		public static readonly Function POWER =
		  new Function(0x151,"power",2);
		public static readonly Function RADIANS =
		  new Function(0x156,"radians",1);
		public static readonly Function DEGREES =
		  new Function(0x157,"degrees",1);
		public static readonly Function SUBTOTAL =
		  new Function(0x158,"subtotal",0xff);
		public static readonly Function SUMIF =
		  new Function(0x159,"sumif",0xff);
		public static readonly Function COUNTIF =
		  new Function(0x15a,"countif",2);
		public static readonly Function COUNTBLANK =
		  new Function(0x15b,"countblank",1);
		public static readonly Function HYPERLINK =
		  new Function(0x167,"hyperlink",2);
		public static readonly Function AVERAGEA =
		  new Function(0x169,"averagea",0xff);
		public static readonly Function MAXA =
		  new Function(0x16a,"maxa",0xff);
		public static readonly Function MINA =
		  new Function(0x16b,"mina",0xff);
		public static readonly Function STDEVPA =
		  new Function(0x16c,"stdevpa",0xff);
		public static readonly Function VARPA =
		  new Function(0x16d,"varpa",0xff);
		public static readonly Function STDEVA =
		  new Function(0x16e,"stdeva",0xff);
		public static readonly Function VARA =
		  new Function(0x16f,"vara",0xff);

		// If token.  This is not an excel assigned number, but one made up
		// in order that the if command may be recognized
		public static readonly Function IF =
		  new Function(0xfffe,"if",0xff);

		// Unknown token
		public static readonly Function UNKNOWN = new Function(0xffff,string.Empty,0);
		}
	}