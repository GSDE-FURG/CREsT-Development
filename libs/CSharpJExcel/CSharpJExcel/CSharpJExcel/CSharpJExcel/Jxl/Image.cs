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

using CSharpJExcel.Jxl.Common;
using CSharpJExcel.Jxl.Write.Biff;


namespace CSharpJExcel.Jxl
	{
	/**
	 * Accessor functions for an image
	 */
	public interface Image
		{
		/**
		 * Accessor for the image position
		 *
		 * @return the column number at which the image is positioned
		 */
		double getColumn();

		/**
		 * Accessor for the image position
		 *
		 * @return the row number at which the image is positioned
		 */
		double getRow();

		/**
		 * Accessor for the image dimensions
		 *
		 * @return  the number of columns this image spans
		 */
		double getWidth();

		/**
		 * Accessor for the image dimensions
		 *
		 * @return the number of rows which this image spans
		 */
		double getHeight();

		/**
		 * Accessor for the image file
		 *
		 * @return the file which the image references
		 */
		System.IO.FileInfo getImageFile();

		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		byte[] getImageData();

		/**
		 * Get the width of this image as rendered within Excel
		 *
		 * @param unit the unit of measurement
		 * @return the width of the image within Excel
		 */
		double getWidth(LengthUnit unit);

		/**
		 * Get the height of this image as rendered within Excel
		 *
		 * @param unit the unit of measurement
		 * @return the height of the image within Excel
		 */
		double getHeight(LengthUnit unit);

		/**
		 * Gets the width of the image.  Note that this is the width of the 
		 * underlying image, and does not take into account any size manipulations
		 * that may have occurred when the image was added into Excel
		 *
		 * @return the image width in pixels
		 */
		int getImageWidth();

		/**
		 * Gets the height of the image.  Note that this is the height of the 
		 * underlying image, and does not take into account any size manipulations
		 * that may have occurred when the image was added into Excel
		 *
		 * @return the image height in pixels
		 */
		int getImageHeight();

		/**
		 * Gets the horizontal resolution of the image, if that information
		 * is available.
		 *
		 * @return the number of dots per unit specified, if available, 0 otherwise
		 */
		double getHorizontalResolution(LengthUnit unit);

		/**
		 * Gets the vertical resolution of the image, if that information
		 * is available.
		 *
		 * @return the number of dots per unit specified, if available, 0 otherwise
		 */
		double getVerticalResolution(LengthUnit unit);
		}
	}
