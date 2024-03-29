/**
 * Copyright (c) 2008, Mounir Jarraï
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *    3. All advertising materials mentioning features or use of this software
 *       must display the following acknowledgement:
 *			This product includes software developed by Mounir Jarraï
 *      	and its contributors.
 *    4. Neither the name Mounir Jarraï nor the names of its contributors may 
 *       be used to endorse or promote products derived from this software 
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY MOUNIR JARRAÏ ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL MOUNIR JARRAÏ BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.mj.eclipse.reporting.classpath.mvc.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.gef.EditPart;
import org.mj.eclipse.reporting.classpath.mvc.models.IConnector;
import org.mj.eclipse.reporting.classpath.mvc.models.IDiagram;
import org.mj.eclipse.reporting.classpath.mvc.models.INode;

/**
 * @author Mounir Jarraï
 *
 */
public final class DiagramPartFactory implements org.eclipse.gef.EditPartFactory {

	private final static Logger logger = Logger.getLogger("DiagramPartFactory");

	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {

		EditPart part = null;
		// Creates edit part
		if (model instanceof IDiagram) {
			part = new DiagramEditpart();
		} else if (model instanceof INode) {
			part = new ProjectEditPart();
		} else if (model instanceof IConnector) {
			part = new UsesConnectorEditPart();
		} else {
			throw new IllegalStateException("No mapping found for " + ((model != null) ? model.getClass().getSimpleName() : "'null'")
					+ " model within " + ((context != null) ? context.getClass().getSimpleName() : "'null'") + " EditPart context!");
		}

		if (logger.isLoggable(Level.FINE)) {
			//$ANALYSIS-IGNORE
			logger.fine(part.getClass().getSimpleName() + " created for model " + model.getClass().getSimpleName());
		}
		// sets the model
		part.setModel(model);
		return part;

	}

}
