/*********************************************************************
*
*      Copyright (C) 2003 Andrew Khan
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


using CSharpJExcel.Jxl.Biff.Drawing;
using System.IO;


namespace CSharpJExcel.Jxl.Write
	{
	/**
	 * Allows an image to be created, or an existing image to be manipulated
	 * Note that co-ordinates and dimensions are given in cells, so that if for
	 * example the width or height of a cell which the image spans is altered,
	 * the image will have a correspondign distortion
	 */
	public class WritableImage : Drawing
		{
		// Shadow these values from the superclass.  The only practical reason
		// for doing this is that they  appear nicely in the javadoc

		/**
		 * Image anchor properties which will move and resize an image
		 * along with the cells
		 */
//		public static readonly ImageAnchorProperties MOVE_AND_SIZE_WITH_CELLS = Drawing.MOVE_AND_SIZE_WITH_CELLS;

		/**
		 * Image anchor properties which will move an image
		 * when cells are inserted or deleted
		 */
//		public static readonly ImageAnchorProperties MOVE_WITH_CELLS = Drawing.MOVE_WITH_CELLS;

		/**
		 * Image anchor properties which will leave an image unaffected when
		 * other cells are inserted, removed or resized
		 */
//		public static readonly ImageAnchorProperties NO_MOVE_OR_SIZE_WITH_CELLS = Drawing.NO_MOVE_OR_SIZE_WITH_CELLS;

		/**
		 * Constructor
		 *
		 * @param x the column number at which to position the image
		 * @param y the row number at which to position the image
		 * @param width the number of columns cells which the image spans
		 * @param height the number of rows which the image spans
		 * @param image the source image file
		 */
		public WritableImage(double x, double y,
							 double width, double height,
							 FileInfo image)
			: base(x, y, width, height, image)
			{
			}

		/**
		 * Constructor
		 *
		 * @param x the column number at which to position the image
		 * @param y the row number at which to position the image
		 * @param width the number of columns cells which the image spans
		 * @param height the number of rows which the image spans
		 * @param imageData the image data
		 */
		public WritableImage(double x,
							 double y,
							 double width,
							 double height,
							 byte[] imageData)
			: base(x, y, width, height, imageData)
			{
			}

		/**
		 * Constructor, used when copying sheets
		 *
		 * @param d the image to copy
		 * @param dg the drawing group
		 */
		public WritableImage(DrawingGroupObject d, DrawingGroup dg)
			: base(d, dg)
			{
			}

		/**
		 * Accessor for the image position
		 *
		 * @return the column number at which the image is positioned
		 */
		public override double getColumn()
			{
			return base.getX();
			}

		/**
		 * Accessor for the image position
		 *
		 * @param c the column number at which the image should be positioned
		 */
		public void setColumn(double c)
			{
			base.setX(c);
			}

		/**
		 * Accessor for the image position
		 *
		 * @return the row number at which the image is positions
		 */
		public override double getRow()
			{
			return base.getY();
			}

		/**
		 * Accessor for the image position
		 *
		 * @param c the row number at which the image should be positioned
		 */
		public void setRow(double c)
			{
			base.setY(c);
			}

		/**
		 * Accessor for the image dimensions
		 *
		 * @return  the number of columns this image spans
		 */
		public override double getWidth()
			{
			return base.getWidth();
			}

		/**
		 * Accessor for the image dimensions
		 * Note that the actual size of the rendered image will depend on the
		 * width of the columns it spans
		 *
		 * @param c the number of columns which this image spans
		 */
		public override void setWidth(double c)
			{
			base.setWidth(c);
			}

		/**
		 * Accessor for the image dimensions
		 *
		 * @return the number of rows which this image spans
		 */
		public override double getHeight()
			{
			return base.getHeight();
			}

		/**
		 * Accessor for the image dimensions
		 * Note that the actual size of the rendered image will depend on the
		 * height of the rows it spans
		 *
		 * @param c the number of rows which this image should span
		 */
		public override void setHeight(double c)
			{
			base.setHeight(c);
			}

		/**
		 * Accessor for the image file
		 *
		 * @return the file which the image references
		 */
		public override FileInfo getImageFile()
			{
			return base.getImageFile();
			}

		/**
		 * Accessor for the image data
		 *
		 * @return the image data
		 */
		public override byte[] getImageData()
			{
			return base.getImageData();
			}

		/**
		 * Accessor for the anchor properties
		 */
		public override void setImageAnchor(ImageAnchorProperties iap)
			{
			base.setImageAnchor(iap);
			}

		/**
		 * Accessor for the anchor properties
		 */
		public override ImageAnchorProperties getImageAnchor()
			{
			return base.getImageAnchor();
			}
		}
	}

