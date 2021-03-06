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
package megan.commands.mapping;

import jloda.gui.commands.ICommand;
import jloda.util.Pair;
import jloda.util.ProgramProperties;
import jloda.util.parse.NexusStreamParser;
import megan.commands.CommandBase;
import megan.core.Director;
import megan.main.MeganProperties;
import megan.viewer.MainViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedList;

public class ListAllChangesCommand extends CommandBase implements ICommand {

    public String getSyntax() {
        return "changeMapping list;";
    }

    public void apply(NexusStreamParser np) throws Exception {
        np.matchIgnoreCase(getSyntax());
        Collection<Pair<String, String>> mappingFixes = new LinkedList<>();
        mappingFixes = ProgramProperties.get(MeganProperties.TAXON_MAPPING_CHANGES, mappingFixes);
        System.out.println("Changed taxon mappings:");
        for (Pair<String, String> pair : mappingFixes) {
            System.out.println("'" + pair.getFirst() + "' maps to " + pair.getSecond());
        }
    }

    public void actionPerformed(ActionEvent event) {
        Director.showMessageWindow();
        executeImmediately(getSyntax());
    }

    public boolean isApplicable() {
        return getViewer() instanceof MainViewer;
    }

    public String getName() {
        return "List All Changes...";
    }

    public String getDescription() {
        return "List all changes";
    }

    public boolean isCritical() {
        return true;
    }

    public ImageIcon getIcon() {
        return null;
    }
}

