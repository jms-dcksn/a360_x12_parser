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

import com.automationanywhere.bot.service.Bot;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.Utils.BackendServer;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.imsweb.x12.Loop;
import com.imsweb.x12.reader.X12Reader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(label = "Get All Loops",
		description = "Get loops x12 File",
		icon = "x12_logo.svg",
		name = "getLoops",
		node_label = "Get All Loops",
		group_label="Parse",
		comment = true,
		return_required = true,
		return_label = "Assign to a List of Strings",
		return_type = LIST,
		return_description = "Each entry in list contains the raw text of each loop contained in X12 file"
		)
public class GetAllLoops {
	@Sessions
	private Map<String, Object> sessionMap;

	@Execute
	public ListValue<StringValue> execute(
			@Idx(index = "1", type = TEXT) @Pkg(label = "Session name", default_value_type = STRING, default_value = "Default")
			@NotEmpty String sessionName
	) {
		BackendServer server = (BackendServer) this.sessionMap.get(sessionName);
		X12Reader reader = server.getReader();
		List<Loop> loops = reader.getLoops();
		List<Value> intListOut = new ArrayList<>();
		ListValue<StringValue> outList = new ListValue<>();
		try {
			for (Iterator<Loop> nextLoop = loops.listIterator(); nextLoop.hasNext(); ) {
				intListOut.add(new StringValue(nextLoop.next().toString()));
			}
			outList.set(intListOut);
		} catch (Exception e) {
			throw new BotCommandException("The input file didn't contain any loops or contained unexpected text. " + e);
		}
		return outList;
	}
	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
