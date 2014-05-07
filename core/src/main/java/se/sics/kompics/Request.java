/**
 * This file is part of the Kompics component model runtime.
 *
 * Copyright (C) 2009 Swedish Institute of Computer Science (SICS) Copyright (C)
 * 2009 Royal Institute of Technology (KTH)
 *
 * Kompics is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 */
package se.sics.kompics;

import java.util.ArrayDeque;

/**
 * The <code>Request</code> class.
 *
 * @author Cosmin Arad <cosmin@sics.se>
 * @author Jim Dowling <jdowling@sics.se>
 * @version $Id$
 */
public abstract class Request implements KompicsEvent {

    ArrayDeque<RequestPathElement> requestPath = new ArrayDeque<RequestPathElement>();

    public void pushPathElement(ChannelCore<?> channel) {
        RequestPathElement pe = new RequestPathElement(channel);
        requestPath.push(pe);
    }

    public void pushPathElement(ComponentCore component) {
        RequestPathElement pe = new RequestPathElement(component);
        RequestPathElement topPE = requestPath.peek();
        if ((topPE != null) && (pe.compareTo(topPE) == 0)) {
            return; // avoid path duplication
        }
        requestPath.push(pe);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Request request = (Request) super.clone();
        request.requestPath = requestPath.clone();
        return request;
    }
}
