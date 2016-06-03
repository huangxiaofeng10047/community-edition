package org.alfresco.solr;

import java.io.IOException;
import java.util.Locale;

import org.alfresco.solr.tracker.Tracker;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;
import org.apache.solr.request.SolrQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.extensions.surf.util.I18NUtil;

/**
 * @author Andy
 *
 */
public class SetLocaleSearchComponent extends SearchComponent
{
    protected final static Logger log = LoggerFactory.getLogger(SetLocaleSearchComponent.class);

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#getDescription()
     */
    @Override
    public String getDescription()
    {
        return "setLocale";
    }

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#getSource()
     */
    @Override
    public String getSource()
    {
        return "";
    }

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#getSourceId()
     */
    @Override
    public String getSourceId()
    {
       return "";
    }

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#getVersion()
     */
    @Override
    public String getVersion()
    {
        return "1";
    }

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#prepare(org.apache.solr.handler.component.ResponseBuilder)
     */
    @Override
    public void prepare(ResponseBuilder rb) throws IOException
    {   
        SolrQueryRequest req = rb.req;
        SolrParams params = req.getParams();
        String localeStr = params.get("locale");
        Locale locale = I18NUtil.parseLocale(localeStr);
        I18NUtil.setLocale(locale);

        AlfrescoCoreAdminHandler adminHandler = (AlfrescoCoreAdminHandler)req.getCore().getCoreDescriptor().getCoreContainer().getMultiCoreHandler();
        Tracker tracker = adminHandler.getTrackers().get(req.getCore().getName());
        if(tracker != null)
        {
            tracker.ensureFirstModelSync();
        }
        
    }

    /* (non-Javadoc)
     * @see org.apache.solr.handler.component.SearchComponent#process(org.apache.solr.handler.component.ResponseBuilder)
     */
    @Override
    public void process(ResponseBuilder rb) throws IOException
    {
        // Nothing to do 

    }

}
