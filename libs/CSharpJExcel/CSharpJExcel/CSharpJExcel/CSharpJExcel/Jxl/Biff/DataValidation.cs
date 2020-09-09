/*********************************************************************
*
*      Copyright (C) 2004 Andrew Khan
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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl;
using CSharpJExcel.Jxl.Biff.Formula;
using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl.Biff
	{
	/**
	 * Class which encapsulates a data validation.  This encapsulates the
	 * base DVAL record (DataValidityListRecord) and all the individual DV
	 * (DataValiditySettingsRecord) records
	 */
	public class DataValidation
		{
		/**
		 * The logger
		 */
		//  private static Logger logger = Logger.getLogger(DataValidation.class);

		/** 
		 * The data validity list
		 */
		private DataValidityListRecord validityList;

		/**
		 * The list of data validity (DV) records
		 */
		private ArrayList validitySettings;

		/**
		 * Handle to the workbook
		 */
		private WorkbookMethods workbook;

		/**

		 * Handle to the external sheet
		 */
		private ExternalSheet externalSheet;

		/**
		 * Handle to the workbook settings
		 */
		private WorkbookSettings workbookSettings;

		/**
		 * The object id of the combo box used for drop downs
		 */
		private uint comboBoxObjectId;

		/**
		 * Indicates whether this was copied
		 */
		private bool copied;

		public const uint DEFAULT_OBJECT_ID = 0xffffffff;
		private const int MAX_NO_OF_VALIDITY_SETTINGS = 0xfffd;

		/**
		 * Constructor
		 */
		public DataValidation(DataValidityListRecord dvlr)
			{
			validityList = dvlr;
			validitySettings = new ArrayList(validityList.getNumberOfSettings());
			copied = false;
			}

		/**
		 * Constructor used to create writable data validations
		 */
		public DataValidation(uint objId,
							  ExternalSheet es,
							  WorkbookMethods wm,
							  WorkbookSettings ws)
			{
			workbook = wm;
			externalSheet = es;
			workbookSettings = ws;
			validitySettings = new ArrayList();
			comboBoxObjectId = objId;
			copied = false;
			}

		/**
		 * Copy constructor used to copy from read to write
		 */
		public DataValidation(DataValidation dv,
							  ExternalSheet es,
							  WorkbookMethods wm,
							  WorkbookSettings ws)
			{
			workbook = wm;
			externalSheet = es;
			workbookSettings = ws;
			copied = true;
			validityList = new DataValidityListRecord(dv.getDataValidityList());

			validitySettings = new ArrayList();
			DataValiditySettingsRecord[] settings = dv.getDataValiditySettings();

			for (int i = 0; i < settings.Length; i++)
				validitySettings.Add(new DataValiditySettingsRecord(settings[i],externalSheet,workbook,workbookSettings));
			}

		/**
		 * Adds a new settings object to this data validation
		 */
		public void add(DataValiditySettingsRecord dvsr)
			{
			validitySettings.Add(dvsr);
			dvsr.setDataValidation(this);

			if (copied)
				{
				// adding a writable dv record to a copied validity list
				Assert.verify(validityList != null);
				validityList.dvAdded();
				}
			}

		/**
		 * Accessor for the validity list.  Used when copying sheets
		 */
		public DataValidityListRecord getDataValidityList()
			{
			return validityList;
			}

		/**
		 * Accessor for the validity settings.  Used when copying sheets
		 */
		public DataValiditySettingsRecord[] getDataValiditySettings()
			{
			DataValiditySettingsRecord[] dvlr = new DataValiditySettingsRecord[validitySettings.Count];
			int pos = 0;
			foreach (DataValiditySettingsRecord record in validitySettings)
				dvlr[pos++] = record;
			return dvlr;
			}

		/**
		 * Writes out the data validation
		 * 
		 * @exception IOException 
		 * @param outputFile the output file
		 */
		public void write(File outputFile)
			{
			if (validitySettings.Count > MAX_NO_OF_VALIDITY_SETTINGS)
				{
				//logger.warn("Maximum number of data validations exceeded - truncating...");
				ArrayList oldValiditySettings = validitySettings;
				validitySettings = new ArrayList(MAX_NO_OF_VALIDITY_SETTINGS);
				for (int count = 0; count < MAX_NO_OF_VALIDITY_SETTINGS; count++)
					validitySettings[count] = oldValiditySettings[count];
				Assert.verify(validitySettings.Count <= MAX_NO_OF_VALIDITY_SETTINGS);
				}

			if (validityList == null)
				{
				DValParser dvp = new DValParser(comboBoxObjectId,validitySettings.Count);
				validityList = new DataValidityListRecord(dvp);
				}

			if (!validityList.hasDVRecords())
				{
				return;
				}

			outputFile.write(validityList);

			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				outputFile.write(dvsr);
			}

		/**
		 * Inserts a row
		 *
		 * @param row the inserted row
		 */
		public void insertRow(int row)
			{
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				dvsr.insertRow(row);
			}

		/**
		 * Removes row
		 *
		 * @param row the  row to be removed
		 */
		public void removeRow(int row)
			{
			ArrayList toRemove = new ArrayList();
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				{
				if (dvsr.getFirstRow() == row && dvsr.getLastRow() == row)
					{
					toRemove.Add(dvsr);
					validityList.dvRemoved();
					}
				else
					dvsr.removeRow(row);
				}
			foreach (DataValiditySettingsRecord dvsr in toRemove)
				validitySettings.Remove(dvsr);
			}

		/**
		 * Inserts a column
		 *
		 * @param col the inserted column
		 */
		public void insertColumn(int col)
			{
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				dvsr.insertColumn(col);
			}

		/**
		 * Removes a column
		 *
		 * @param col the inserted column
		 */
		public void removeColumn(int col)
			{
			ArrayList toRemove = new ArrayList();
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				{
				if (dvsr.getFirstColumn() == col && dvsr.getLastColumn() == col)
					{
					toRemove.Add(dvsr);
					validityList.dvRemoved();
					}
				else
					dvsr.removeColumn(col);
				}
			foreach (DataValiditySettingsRecord dvsr in toRemove)
				validitySettings.Remove(dvsr);
			}

		/**
		 * Removes the data validation for a specific cell
		 *
		 * @param col the column
		 * @param row the row
		 */
		public void removeDataValidation(int col,int row)
			{
			ArrayList toRemove = new ArrayList();
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				{
				if (dvsr.getFirstColumn() == col && dvsr.getLastColumn() == col &&
					dvsr.getFirstRow() == row && dvsr.getLastRow() == row)
					{
					toRemove.Add(dvsr);
					validityList.dvRemoved();
					break;
					}
				}
			foreach (DataValiditySettingsRecord dvsr in toRemove)
				validitySettings.Remove(dvsr);
			}

		/**
		 * Removes the data validation for a specific cell
		 *
		 * @param col1 the first column
		 * @param row1 the first row
		 */
		public void removeSharedDataValidation(int col1,int row1,int col2,int row2)
			{
			ArrayList toRemove = new ArrayList();
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				{
				if (dvsr.getFirstColumn() == col1 && dvsr.getLastColumn() == col2 &&
					dvsr.getFirstRow() == row1 && dvsr.getLastRow() == row2)
					{
					toRemove.Add(dvsr);
					validityList.dvRemoved();
					break;
					}
				}
			foreach (DataValiditySettingsRecord dvsr in toRemove)
				validitySettings.Remove(dvsr);
			}

		/**
		 * Used during the copy process to retrieve the validity settings for
		 * a particular cell
		 */
		public DataValiditySettingsRecord getDataValiditySettings(int col,int row)
			{
			foreach (DataValiditySettingsRecord dvsr in validitySettings)
				{
				if (dvsr.getFirstColumn() == col && dvsr.getFirstRow() == row)
					return dvsr;
				}

			return null;
			}

		/**
		 * Accessor for the combo box, used when copying sheets
		 */
		public uint getComboBoxObjectId()
			{
			return comboBoxObjectId;
			}
		}
	}
