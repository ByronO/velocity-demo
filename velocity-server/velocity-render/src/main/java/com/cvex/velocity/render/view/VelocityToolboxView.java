package com.cvex.velocity.render.view;

import com.cvex.velocity.render.context.ChainedContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

public class VelocityToolboxView extends VelocityView {

    private String toolboxConfigLocation;


    /**
     * Set a Velocity Toolbox config location, for example "/WEB-INF/toolbox.xml",
     * to automatically load a Velocity Tools toolbox definition file and expose
     * all defined tools in the specified scopes. If no config location is
     * specified, no toolbox will be loaded and exposed.
     * <p>The specified location string needs to refer to a ServletContext
     * resource, as expected by ServletToolboxManager which is part of
     * the view package of Velocity Tools.
     * @see
     */
    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

    /**
     * Return the Velocity Toolbox config location, if any.
     */
    protected String getToolboxConfigLocation() {
        return this.toolboxConfigLocation;
    }


    /**
     * Overridden to create a ChainedContext, which is part of the view package
     * of Velocity Tools, as special context. ChainedContext is needed for
     * initialization of ViewTool instances.
     * @see #initTool
     */
    @Override
    protected Context createVelocityContext(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ChainedContext(
                new VelocityContext(model), getVelocityEngine(), request, response, getServletContext());
    }

    /**
     * Overridden to check for the ViewContext interface which is part of the
     * view package of Velocity Tools. This requires a special Velocity context,
     * like ChainedContext as set up by {@link #createVelocityContext} in this class.
     */
    @Override
    protected void initTool(Object tool, Context velocityContext) throws Exception {
        // Velocity Tools 1.3: a class-level "init(Object)" method.
        Method initMethod = ClassUtils.getMethodIfAvailable(tool.getClass(), "init", Object.class);
        if (initMethod != null) {
            ReflectionUtils.invokeMethod(initMethod, tool, velocityContext);
        }
    }
}
