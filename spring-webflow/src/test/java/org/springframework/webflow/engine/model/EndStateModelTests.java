/*
 * Copyright 2004-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.webflow.engine.model;

import java.util.LinkedList;

import junit.framework.TestCase;

/**
 * Unit tests for {@link EndStateModel}.
 */
public class EndStateModelTests extends TestCase {

	public void testMergeable() {
		EndStateModel child = new EndStateModel("child");
		assertTrue(child.isMergeableWith(child));
	}

	public void testNotMergeable() {
		EndStateModel child = new EndStateModel("child");
		EndStateModel parent = new EndStateModel("parent");
		assertFalse(child.isMergeableWith(parent));
	}

	public void testNotMergeableWithNull() {
		EndStateModel child = new EndStateModel("child");
		assertFalse(child.isMergeableWith(null));
	}

	public void testMerge() {
		EndStateModel child = new EndStateModel("child");
		EndStateModel parent = new EndStateModel("child");
		parent.setCommit("true");
		parent.setView("view");

		LinkedList<OutputModel> outputs = new LinkedList<OutputModel>();
		outputs.add(new OutputModel("foo", "bar"));
		parent.setOutputs(outputs);

		child.merge(parent);
		assertEquals("true", child.getCommit());
		assertEquals("bar", child.getOutputs().get(0).getValue());
	}
}
