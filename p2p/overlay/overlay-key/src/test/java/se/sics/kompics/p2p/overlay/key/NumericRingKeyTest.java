/**
 * This file is part of the Kompics P2P Framework.
 * 
 * Copyright (C) 2009 Swedish Institute of Computer Science (SICS)
 * Copyright (C) 2009 Royal Institute of Technology (KTH)
 *
 * Kompics is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package se.sics.kompics.p2p.overlay.key;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import se.sics.kompics.p2p.overlay.key.RingKey.IntervalBounds;

/**
 * The <code>NumericRingKeyTest</code> class.
 * 
 * @author Cosmin Arad <cosmin@sics.se>
 * @version $Id$
 */
public class NumericRingKeyTest {

	@Test
	public void testBelongsTo() {
		BigInteger size = new BigInteger("16");
		NumericRingKey key0 = new NumericRingKey(0);
		NumericRingKey key8 = new NumericRingKey(8);

		Assert.assertTrue(key8.belongsTo(key0, key0, IntervalBounds.OPEN_OPEN,
				size));
		Assert.assertTrue(key8.belongsTo(key0, key0,
				IntervalBounds.OPEN_CLOSED, size));
		Assert.assertTrue(key8.belongsTo(key0, key0,
				IntervalBounds.CLOSED_OPEN, size));
		Assert.assertFalse(key8.belongsTo(key0, key0,
				IntervalBounds.CLOSED_CLOSED, size));

		Assert.assertFalse(key0.belongsTo(key0, key0, IntervalBounds.OPEN_OPEN,
				size));
		Assert.assertTrue(key0.belongsTo(key0, key0,
				IntervalBounds.OPEN_CLOSED, size));
		Assert.assertTrue(key0.belongsTo(key0, key0,
				IntervalBounds.CLOSED_OPEN, size));
		Assert.assertTrue(key0.belongsTo(key0, key0,
				IntervalBounds.CLOSED_CLOSED, size));
	}

	@Test
	public void testSymmetricKey() {
		BigInteger size = new BigInteger("16");

		NumericRingKey key1 = new NumericRingKey(1);
		NumericRingKey key5 = new NumericRingKey(5);
		NumericRingKey key9 = new NumericRingKey(9);
		NumericRingKey key13 = new NumericRingKey(13);

		Assert.assertEquals(key1, key1.getSymmetricKey(0, 4, size));
		Assert.assertEquals(key5, key1.getSymmetricKey(1, 4, size));
		Assert.assertEquals(key9, key1.getSymmetricKey(2, 4, size));
		Assert.assertEquals(key13, key1.getSymmetricKey(3, 4, size));

		Assert.assertEquals(key5, key5.getSymmetricKey(0, 4, size));
		Assert.assertEquals(key9, key5.getSymmetricKey(1, 4, size));
		Assert.assertEquals(key13, key5.getSymmetricKey(2, 4, size));
		Assert.assertEquals(key1, key5.getSymmetricKey(3, 4, size));

		Assert.assertEquals(key9, key9.getSymmetricKey(0, 4, size));
		Assert.assertEquals(key13, key9.getSymmetricKey(1, 4, size));
		Assert.assertEquals(key1, key9.getSymmetricKey(2, 4, size));
		Assert.assertEquals(key5, key9.getSymmetricKey(3, 4, size));

		Assert.assertEquals(key13, key13.getSymmetricKey(0, 4, size));
		Assert.assertEquals(key1, key13.getSymmetricKey(1, 4, size));
		Assert.assertEquals(key5, key13.getSymmetricKey(2, 4, size));
		Assert.assertEquals(key9, key13.getSymmetricKey(3, 4, size));
	}
}
