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

using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl.Biff.Drawing
	{
	/**
	 * Interface for the various object types that can be added to a drawing 
	 * group
	 */
	public interface DrawingGroupObject
		{
		/**
		 * Sets the object id.  Invoked by the drawing group when the object is 
		 * added to id
		 *
		 * @param objid the object id
		 * @param bip the blip id
		 * @param sid the shape id
		 */
		void setObjectId(uint objid,int bip,int sid);

		/**
		 * Accessor for the object id
		 *
		 * @return the object id
		 */
		uint getObjectId();

		/**
		 * Accessor for the blip id
		 *
		 * @return the blip id
		 */
		int getBlipId();

		/**
		 * Accessor for the shape id
		 *
		 * @return the shape id
		 */
		int getShapeId();


		/**
		 * Gets the drawing record which was read in
		 *
		 * @return the drawing record
		 */
		MsoDrawingRecord getMsoDrawingRecord();

		/**
		 * Creates the main Sp container for the drawing
		 *
		 * @return the SP container
		 */
		EscherContainer getSpContainer();

		/**
		 * Sets the drawing group for this drawing.  Called by the drawing group
		 * when this drawing is added to it
		 *
		 * @param dg the drawing group
		 */
		void setDrawingGroup(DrawingGroup dg);

		/**
		 * Accessor for the drawing group
		 *
		 * @return the drawing group
		 */
		DrawingGroup getDrawingGroup();

		/**
		 * Gets the origin of this drawing
		 *
		 * @return where this drawing came from
		 */
		Origin getOrigin();

		/**
		 * Accessor for the reference count on this drawing
		 *
		 * @return the reference count
		 */
		int getReferenceCount();

		/**
		 * Sets the new reference count on the drawing
		 *
		 * @param r the new reference count
		 */
		void setReferenceCount(int r);

		/**
		 * Accessor for the column of this drawing
		 *
		 * @return the column
		 */
		double getX();

		/**
		 * Sets the column position of this drawing
		 *
		 * @param x the column
		 */
		void setX(double x);

		/**
		 * Accessor for the row of this drawing
		 *
		 * @return the row
		 */
		double getY();

		/**
		 * Accessor for the row of the drawing
		 *
		 * @param y the row
		 */
		void setY(double y);

		/**
		 * Accessor for the width of this drawing
		 *
		 * @return the number of columns spanned by this image
		 */
		double getWidth();

		/**
		 * Accessor for the width
		 *
		 * @param w the number of columns to span
		 */
		void setWidth(double w);

		/**
		 * Accessor for the height of this drawing
		 *
		 * @return the number of rows spanned by this image
		 */
		double getHeight();

		/**
		 * Accessor for the height of this drawing
		 *
		 * @param h the number of rows spanned by this image
		 */
		void setHeight(double h);


		/**
		 * Accessor for the type
		 *
		 * @return the type
		 */
		ShapeType getType();

		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		byte[] getImageData();

		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		byte[] getImageBytes();

		/** 
		 * Accessor for the image file path.  Normally this is the absolute path
		 * of a file on the directory system, but if this drawing was constructed
		 * using an byte[] then the blip id is returned
		 *
		 * @return the image file path, or the blip id
		 */
		string getImageFilePath();

		/**
		 * Writes any other records associated with this drawing group object
		 */
		void writeAdditionalRecords(File outputFile);

		/**
		 * Writes any records that need to be written after all the drawing group
		 * objects have been written
		 */
		void writeTailRecords(File outputFile);

		/**
		 * Accessor for the first drawing on the sheet.  This is used when
		 * copying unmodified sheets to indicate that this drawing contains
		 * the first time Escher gubbins
		 *
		 * @return TRUE if this MSORecord is the first drawing on the sheet
		 */
		bool isFirst();

		/**
		 * Queries whether this object is a form object.  Form objects have their
		 * drawings records spread over TXO and CONTINUE records and 
		 * require special handling
		 * 
		 * @return TRUE if this is a form object, FALSE otherwise
		 */
		bool isFormObject();
		}
	}



