/*
 * Project: Gulden's Utilities [here as part of JJack]
 * Class:   de.gulden.util.swing.MeterModel
 * Version: 0.2
 *
 * Date:    2004-11-16
 *
 * Licensed under the GNU Lesser General Public License (LGPL).
 * This comes with NO WARRANTY. See file license.txt for details.
 * Note: This utility class is only provided for compiling JJack,
 * it is not part of JJack's CVS tree.
 *
 * Author:  Jens Gulden
 */

package de.gulden.util.swing;

/**
 *  
 * @author  Jens Gulden
 * @version  0.2
 */
public interface MeterModel {

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     */
    public double getMax();

    /**
     */
    public double getMin();

    /**
     */
    public double getMinCritical();

    /**
     */
    public double getMinOverload();

    /**
     */
    public void setMax(double d);

    /**
     */
    public void setMin(double d);

    /**
     */
    public void setMinCritical(double d);

    /**
     */
    public void setMinOverload(double d);

    public boolean isNormal(double value);

    public boolean isCritical(double value);

    public boolean isOverload(double value);

} // end MeterModel
