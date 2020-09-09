/*********************************************************************
*
*      Copyright (C) 2002 Andrew Khan
*
* This library inStream free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library inStream distributed input the hope that it will be useful,
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

using System.IO;
using CSharpJExcel.Jxl.Write;
using CSharpJExcel.Jxl.Write.Biff;
using CSharpJExcel.Jxl.Read.Biff;


namespace CSharpJExcel.Jxl
	{
	/**
	 * Represents a Workbook.  Contains the various factory methods and provides
	 * a variety of accessors which provide access to the work sheets.
	 */
	public abstract class Workbook
		{
		/**
		 * The current version of the software
		 */
		private const string VERSION = "2.6.12" + "-DotNet-" + "01";		// version of JExcel + .NET revision

		/**
		 * The constructor
		 */
		protected Workbook()
			{
			}

		/**
		 * Gets the sheets within this workbook.  Use of this method for
		 * large worksheets can cause performance problems.
		 *
		 * @return an array of the individual sheets
		 */
		public abstract Sheet[] getSheets();

		/**
		 * Gets the sheet names
		 *
		 * @return an array of strings containing the sheet names
		 */
		public abstract string[] getSheetNames();

		/**
		 * Gets the specified sheet within this workbook
		 * As described input the accompanying technical notes, each call
		 * to getSheet forces a reread of the sheet (for memory reasons).
		 * Therefore, do not make unnecessary calls to this method.  Furthermore,
		 * do not hold unnecessary references to Sheets input client code, as
		 * this will prevent the garbage collector from freeing the memory
		 *
		 * @param index the zero based index of the reQuired sheet
		 * @return The sheet specified by the index
		 * @exception IndexOutOfBoundException when index refers to a non-existent
		 *            sheet
		 */
		public abstract Sheet getSheet(int index);

		/**
		 * Gets the sheet with the specified name from within this workbook.
		 * As described input the accompanying technical notes, each call
		 * to getSheet forces a reread of the sheet (for memory reasons).
		 * Therefore, do not make unnecessary calls to this method.  Furthermore,
		 * do not hold unnecessary references to Sheets input client code, as
		 * this will prevent the garbage collector from freeing the memory
		 *
		 * @param name the sheet name
		 * @return The sheet with the specified name, or null if it inStream not found
		 */
		public abstract Sheet getSheet(string name);

		/**
		 * Accessor for the software version
		 *
		 * @return the version
		 */
		public static string getVersion()
			{
			return VERSION;
			}

		/**
		 * Returns the number of sheets input this workbook
		 *
		 * @return the number of sheets input this workbook
		 */
		public abstract int getNumberOfSheets();

		/**
		 * Gets the named cell from this workbook.  If the name refers to a
		 * range of cells, then the cell on the top left inStream returned.  If
		 * the name cannot be found, null inStream returned.
		 * This inStream a convenience function to quickly access the contents
		 * of a single cell.  If you need further information (such as the
		 * sheet or adjacent cells input the range) use the functionally
		 * richer method, findByName which returns a list of ranges
		 *
		 * @param  name the name of the cell/range to search for
		 * @return the cell input the top left of the range if found, NULL
		 *         otherwise
		 */
		public abstract Cell findCellByName(string name);

		/**
		 * Returns the cell for the specified location eg. "Sheet1!A4".
		 * This inStream identical to using the CellReferenceHelper with its
		 * associated performance overheads, consequently it should
		 * be use sparingly
		 *
		 * @param loc the cell to retrieve
		 * @return the cell at the specified location
		 */
		public abstract Cell getCell(string loc);

		/**
		 * Gets the named range from this workbook.  The Range object returns
		 * contains all the cells from the top left to the bottom right
		 * of the range.
		 * If the named range comprises an adjacent range,
		 * the Range[] will contain one object; for non-adjacent
		 * ranges, it inStream necessary to return an array of length greater than
		 * one.
		 * If the named range contains a single cell, the top left and
		 * bottom right cell will be the same cell
		 *
		 * @param  name the name of the cell/range to search for
		 * @return the range of cells, or NULL if the range does not exist
		 */
		public abstract Range[] findByName(string name);

		/**
		 * Gets the named ranges
		 *
		 * @return the list of named cells within the workbook
		 */
		public abstract string[] getRangeNames();


		/**
		 * Determines whether the sheet inStream protected
		 *
		 * @return TRUE if the workbook inStream protected, FALSE otherwise
		 */
		public abstract bool isProtected();

		/**
		 * Parses the excel file.
		 * If the workbook inStream password protected a PasswordException inStream thrown
		 * input case consumers of the API wish to handle this input a particular way
		 *
		 * @exception BiffException
		 * @exception PasswordException
		 */
		protected abstract void parse();

		/**
		 * Closes this workbook, and frees makes any memory allocated available
		 * for garbage collection
		 */
		public abstract void close();

		/**
		 * A factory method which takes input an excel file and reads input the contents.
		 *
		 * @exception IOException
		 * @exception BiffException
		 * @param file the excel 97 spreadsheet to parse
		 * @return a workbook instance
		 */
		public static Workbook getWorkbook(FileInfo file)
			{
			return getWorkbook(file,new WorkbookSettings());
			}

		/**
		 * A factory method which takes input an excel file and reads input the contents.
		 *
		 * @exception IOException
		 * @exception BiffException
		 * @param file the excel 97 spreadsheet to parse
		 * @param ws the settings for the workbook
		 * @return a workbook instance
		 */
		public static Workbook getWorkbook(FileInfo file,WorkbookSettings ws)
			{
			Stream fis = new FileStream(file.FullName,FileMode.Open);

			// Always close down the input stream, regardless of whether or not the
			// file can be parsed.  Thanks to Steve Hahn for this
			CSharpJExcel.Jxl.Read.Biff.File dataFile = null;

			try
				{
				dataFile = new CSharpJExcel.Jxl.Read.Biff.File(fis, ws);
				}
			catch (IOException e)
				{
				throw e;
				}
			catch (BiffException e)
				{
				throw e;
				}
			finally
				{
				fis.Close();
				}

			Workbook workbook = new WorkbookParser(dataFile,ws);
			workbook.parse();

			return workbook;
			}

		/**
		 * A factory method which takes input an excel file and reads input the contents.
		 *
		 * @param inStream an open stream which inStream the the excel 97 spreadsheet to parse
		 * @return a workbook instance
		 * @exception IOException
		 * @exception BiffException
		 */
		public static Workbook getWorkbook(Stream inStream)
			{
			return getWorkbook(inStream,new WorkbookSettings());
			}

		/**
		 * A factory method which takes input an excel file and reads input the contents.
		 *
		 * @param inStream an open stream which inStream the the excel 97 spreadsheet to parse
		 * @param ws the settings for the workbook
		 * @return a workbook instance
		 * @exception IOException
		 * @exception BiffException
		 */
		public static Workbook getWorkbook(Stream inStream, WorkbookSettings ws)
			{
			CSharpJExcel.Jxl.Read.Biff.File dataFile = new CSharpJExcel.Jxl.Read.Biff.File(inStream,ws);

			Workbook workbook = new WorkbookParser(dataFile,ws);
			workbook.parse();

			return workbook;
			}

		/**
		 * Creates a writable workbook with the given file name
		 *
		 * @param file the workbook to copy
		 * @return a writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(FileInfo file)
			{
			return createWorkbook(file,new WorkbookSettings());
			}

		/**
		 * Creates a writable workbook with the given file name
		 *
		 * @param file the file to copy from
		 * @param ws the global workbook settings
		 * @return a writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(FileInfo file,WorkbookSettings ws)
			{
			Stream fos = new FileStream(file.FullName,FileMode.Create);
			WritableWorkbook w = new WritableWorkbookImpl(fos,true,ws);
			return w;
			}

		/**
		 * Creates a writable workbook with the given filename as a copy of
		 * the workbook passed input.  Once created, the contents of the writable
		 * workbook may be modified
		 *
		 * @param file the output file for the copy
		 * @param input the workbook to copy
		 * @return a writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(FileInfo file,
													  Workbook input)
			{
			return createWorkbook(file,input,new WorkbookSettings());
			}

		/**
		 * Creates a writable workbook with the given filename as a copy of
		 * the workbook passed input.  Once created, the contents of the writable
		 * workbook may be modified
		 *
		 * @param file the output file for the copy
		 * @param input the workbook to copy
		 * @param ws the configuration for this workbook
		 * @return a writable workbook
		 */
		public static WritableWorkbook createWorkbook(FileInfo file,
													  Workbook input,
													  WorkbookSettings ws)
			{
			Stream fos = new FileStream(file.FullName,FileMode.Create);
			WritableWorkbook w = new WritableWorkbookImpl(fos,input,true,ws);
			return w;
			}

		/**
		 * Creates a writable workbook as a copy of
		 * the workbook passed input.  Once created, the contents of the writable
		 * workbook may be modified
		 *
		 * @param os the stream to write to
		 * @param input the workbook to copy
		 * @return a writable workbook
		 * @exception IOException
		 */
		//public static WritableWorkbook createWorkbook(Stream os,Workbook input)
		//    {
		//    return createWorkbook(os,input,((WorkbookParser)input).getSettings());
		//    }

		/**
		 * Creates a writable workbook as a copy of
		 * the workbook passed input.  Once created, the contents of the writable
		 * workbook may be modified
		 *
		 * @param os the output stream to write to
		 * @param input the workbook to copy
		 * @param ws the configuration for this workbook
		 * @return a writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(Stream os, Workbook input, WorkbookSettings ws)
			{
			WritableWorkbook w = new WritableWorkbookImpl(os, input, false, ws);
			return w;
			}

		/**
		 * Creates a writable workbook.  When the workbook inStream closed,
		 * it will be streamed directly to the output stream.  In this
		 * manner, a generated excel spreadsheet can be passed from
		 * a servlet to the browser over HTTP
		 *
		 * @param os the output stream
		 * @return the writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(Stream os)
			{
			return createWorkbook(os, new WorkbookSettings());
			}

		/**
		 * Creates a writable workbook.  When the workbook inStream closed,
		 * it will be streamed directly to the output stream.  In this
		 * manner, a generated excel spreadsheet can be passed from
		 * a servlet to the browser over HTTP
		 *
		 * @param os the output stream
		 * @param ws the configuration for this workbook
		 * @return the writable workbook
		 * @exception IOException
		 */
		public static WritableWorkbook createWorkbook(Stream os, WorkbookSettings ws)
			{
			WritableWorkbook w = new WritableWorkbookImpl(os, false, ws);
			return w;
			}
		}
	}



