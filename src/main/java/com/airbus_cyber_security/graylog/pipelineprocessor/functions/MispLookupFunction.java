/*
 * Copyright (C) 2018 Airbus CyberSecurity (SAS)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package com.airbus_cyber_security.graylog.pipelineprocessor.functions;

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;

public class MispLookupFunction extends AbstractFunction<Boolean> {

    public static final String NAME = "misp_lookup";
    
    private final ParameterDescriptor<String, String> attributeTypeParameter = ParameterDescriptor.string("attribute_type").description("The type of attribute to look for in MISP.").build();

    @Override
    public Boolean evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
        return false;
    }

    @Override
    public FunctionDescriptor<Boolean> descriptor() {
        return FunctionDescriptor.<Boolean>builder()
                .name(NAME)
                .description("Searches for the presence of an attribute with given attribute type in MISP.")
                .params(attributeTypeParameter)
                .returnType(Boolean.class)
                .build();
    }
}
