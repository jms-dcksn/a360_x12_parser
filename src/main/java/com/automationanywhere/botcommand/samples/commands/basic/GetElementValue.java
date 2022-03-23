/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 * @author James Dickson
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.Map;

import com.automationanywhere.botcommand.samples.Utils.BackendServer;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.imsweb.x12.Loop;
import com.imsweb.x12.reader.X12Reader;


@BotCommand
@CommandPkg(label = "Get Element Value",
		description = "Get an element from x12 File",
		icon = "x12_logo.svg",
		name = "getElement",
		node_label = "Get Element Value",
		group_label="Parse",
		comment = true,
		return_required = true,
		return_label = "Assign to string",
		return_type = STRING
		)
public class GetElementValue {
	@Sessions
	private Map<String, Object> sessionMap;

	@Execute
	public StringValue execute(
			@Idx(index = "1", type = TEXT) @Pkg(label = "Session name", default_value_type = STRING, default_value = "Default")
			@NotEmpty String sessionName,
			@Idx(index = "2", type = NUMBER) @Pkg(label = "Loop Index", default_value_type = DataType.NUMBER, default_value = "0",
			description = "If X12 file contains multiple loops, use this to parse specific indices, or inside an iterator. Leaving this empty will default to return the first" +
					"loop from the file.")
			Double loopIndex,
			@Idx(index = "3", type = TEXT) @Pkg(label = "Loop", default_value_type = STRING)
			String loop,
			@Idx(index = "4", type = TEXT) @Pkg(label = "Segment", default_value_type = STRING)
			String segment,
			@Idx(index = "5", type = TEXT) @Pkg(label = "Element ID", default_value_type = STRING)
			@NotEmpty String element
	) {
		BackendServer server = (BackendServer) this.sessionMap.get(sessionName);
		X12Reader reader = server.getReader();
		Loop x12loop = reader.getLoops().get(loopIndex.intValue());
		String data = x12loop
				.getLoop(loop)
				.getSegment(segment)
				.getElementValue(element);
		return new StringValue(data);
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
