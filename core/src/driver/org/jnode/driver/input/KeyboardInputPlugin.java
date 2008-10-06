/*
 * $Id: TextScreenConsolePlugin.java 4556 2008-09-13 08:02:20Z crawley $
 *
 * JNode.org
 * Copyright (C) 2003-2006 JNode.org
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; If not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jnode.driver.input;

import javax.naming.NamingException;

import org.jnode.naming.InitialNaming;
import org.jnode.plugin.Plugin;
import org.jnode.plugin.PluginDescriptor;
import org.jnode.plugin.PluginException;

/**
 * The keyboard input plugin defines an extension point that is used to 
 * register keyboard interpreters, and hosts the keyboard layout manager.
 * 
 * @author crawley@jnode.org
 */
public class KeyboardInputPlugin extends Plugin {
    
    private KeyboardLayoutManager mgr;
    
    public KeyboardInputPlugin(PluginDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    protected void startPlugin() throws PluginException {
        try {
            mgr = new KeyboardLayoutManager(
                    getDescriptor().getExtensionPoint("keyboard-layouts"));
            InitialNaming.bind(KeyboardLayoutManager.NAME, mgr);
        } catch (NamingException ex) {
            throw new PluginException(ex);
        }
    }

    @Override
    protected void stopPlugin() throws PluginException {
        // Nothing needs to be done
    }

}