/*
 *  Copyright (C) 2017 Daniel H. Huson
 *
 *  (Some files contain contributions from other authors, who are then mentioned separately.)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package megan.util;

import jloda.util.Basic;
import jloda.util.FileFilterBase;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A RDP standalone file in text format filter
 * Daniel Huson         4.2015
 */
public class RDPStandaloneFileFilter extends FileFilterBase implements FilenameFilter {
    static private RDPStandaloneFileFilter instance;

    public static RDPStandaloneFileFilter getInstance() {
        if (instance == null) {
            instance = new RDPStandaloneFileFilter();
            instance.setAllowGZipped(true);
            instance.setAllowZipped(true);
        }
        return instance;
    }

    private RDPStandaloneFileFilter() {
        add("txt");
        add("rdp");
    }

    /**
     * @return description of file matching the filter
     */
    public String getBriefDescription() {
        return "RDP standalone files";
    }

    /**
     * is file acceptable?
     *
     * @param directory
     * @param fileName
     * @return true if acceptable
     */
    @Override
    public boolean accept(File directory, String fileName) {
        if (!super.accept(directory, fileName))
            return false;
        String[] firstLines = Basic.getFirstLinesFromFile(new File(fileName), 2);
        return firstLines != null && firstLines.length == 2 && firstLines[0].startsWith(">") && Basic.contains(firstLines[1], ';', 2)
                && firstLines[1].toLowerCase().contains("root");
    }
}
