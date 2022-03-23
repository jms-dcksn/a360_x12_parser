package com.automationanywhere.botcommand.samples.commands.basic;
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

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.Utils.BackendServer;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.imsweb.x12.reader.X12Reader;

import java.io.File;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

@BotCommand
@CommandPkg(label = "End Session",
        description = "Ends Session",
        icon = "x12_logo.svg",
        name = "endx12session",
        node_label = "End Session {{sessionName}}",
        group_label="Admin",
        comment = true
)

public class B_EndSession {
    @Sessions
    private Map<String, Object> sessionMap;
    @Execute
    public void execute(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Start Session", default_value_type = STRING, default_value = "Default")
            @NotEmpty String sessionName
    ) {
        sessionMap.remove(sessionName);
    }
    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
