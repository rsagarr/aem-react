package com.react.demo.core.models.v1;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.react.demo.core.models.CustomComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = { CustomComponent.class, ComponentExporter.class },
        resourceType = CustomComponentImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class CustomComponentImpl implements CustomComponent {

    static final String RESOURCE_TYPE = "mysite/components/custom-component";

    @ValueMapValue
    private String text;

    // This function is important to export JSON depending on resourcetype.
    @Override
    public String getExportedType() {
        return CustomComponentImpl.RESOURCE_TYPE;
    }


    @Override
    public String getText() {
        return StringUtils.isNotBlank(text) ? text.toUpperCase() : null;
    }
}

