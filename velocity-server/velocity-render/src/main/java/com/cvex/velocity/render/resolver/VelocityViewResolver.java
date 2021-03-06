package com.cvex.velocity.render.resolver;

import org.springframework.web.servlet.view.AbstractTemplateView;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class VelocityViewResolver extends AbstractTemplateViewResolver {
    private boolean exposeRequestAttributes = false;

    private boolean allowRequestOverride = false;

    private boolean exposeSessionAttributes = false;

    private boolean allowSessionOverride = false;

    private boolean exposeSpringMacroHelpers = true;

    @Override
    protected Class<?> requiredViewClass() {
        return AbstractTemplateView.class;
    }

    /**
     * Set whether all request attributes should be added to the
     * model prior to merging with the template. Default is "false".
     * @see AbstractTemplateView#setExposeRequestAttributes
     */
    public void setExposeRequestAttributes(boolean exposeRequestAttributes) {
        this.exposeRequestAttributes = exposeRequestAttributes;
    }

    /**
     * Set whether HttpServletRequest attributes are allowed to override (hide)
     * controller generated model attributes of the same name. Default is "false",
     * which causes an exception to be thrown if request attributes of the same
     * name as model attributes are found.
     * @see AbstractTemplateView#setAllowRequestOverride
     */
    public void setAllowRequestOverride(boolean allowRequestOverride) {
        this.allowRequestOverride = allowRequestOverride;
    }

    /**
     * Set whether all HttpSession attributes should be added to the
     * model prior to merging with the template. Default is "false".
     * @see AbstractTemplateView#setExposeSessionAttributes
     */
    public void setExposeSessionAttributes(boolean exposeSessionAttributes) {
        this.exposeSessionAttributes = exposeSessionAttributes;
    }

    /**
     * Set whether HttpSession attributes are allowed to override (hide)
     * controller generated model attributes of the same name. Default is "false",
     * which causes an exception to be thrown if session attributes of the same
     * name as model attributes are found.
     * @see AbstractTemplateView#setAllowSessionOverride
     */
    public void setAllowSessionOverride(boolean allowSessionOverride) {
        this.allowSessionOverride = allowSessionOverride;
    }

    /**
     * Set whether to expose a RequestContext for use by Spring's macro library,
     * under the name "springMacroRequestContext". Default is "true".
     * @see AbstractTemplateView#setExposeSpringMacroHelpers
     */
    public void setExposeSpringMacroHelpers(boolean exposeSpringMacroHelpers) {
        this.exposeSpringMacroHelpers = exposeSpringMacroHelpers;
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractTemplateView view = (AbstractTemplateView) super.buildView(viewName);
        view.setExposeRequestAttributes(this.exposeRequestAttributes);
        view.setAllowRequestOverride(this.allowRequestOverride);
        view.setExposeSessionAttributes(this.exposeSessionAttributes);
        view.setAllowSessionOverride(this.allowSessionOverride);
        view.setExposeSpringMacroHelpers(this.exposeSpringMacroHelpers);
        return view;
    }
}
