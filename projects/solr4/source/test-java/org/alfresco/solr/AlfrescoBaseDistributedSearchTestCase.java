/*
 * #%L
 * Alfresco Solr 4
 * %%
 * Copyright (C) 2005 - 2016 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software. 
 * If the software was purchased under a paid Alfresco license, the terms of 
 * the paid license agreement will prevail.  Otherwise, the software is 
 * provided under the following open source license terms:
 * 
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

package org.alfresco.solr;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.apache.solr.BaseDistributedSearchTestCase;
import org.apache.solr.SolrTestCaseJ4;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.common.util.ContentStreamBase;

/**
 * @author Joel
 */

@SolrTestCaseJ4.SuppressSSL
public abstract class AlfrescoBaseDistributedSearchTestCase extends BaseDistributedSearchTestCase
{
    public AlfrescoBaseDistributedSearchTestCase()
    {
        System.setProperty("alfresco.test", "true");
    }

    public String getSolrHome()
    {
        return AlfrescoSolrTestCaseJ4.HOME().getAbsolutePath();
    }

    public static class AlfrescoQueryRequest extends QueryRequest
    {
        private String json;

        public AlfrescoQueryRequest(String json, SolrParams params)
        {
            super(params);
            this.json =json;
        }

        public Collection<ContentStream> getContentStreams()
        {
            List streams = new ArrayList();
            streams.add(new ContentStreamBase.StringStream(json));
            return streams;
        }
    }

    protected QueryRequest getAlfrescoRequest(String json, SolrParams params) {
        QueryRequest request = new AlfrescoQueryRequest(json, params);
        request.setMethod(SolrRequest.METHOD.POST);
        return request;
    }

    /**
     * Returns the QueryResponse from {@link #queryServer}
     */
    protected QueryResponse query(String json, ModifiableSolrParams params) throws Exception
    {
        params.set("distrib", "false");
        QueryRequest request = getAlfrescoRequest(json, params);
        QueryResponse controlRsp = request.process(controlClient);
        validateControlData(controlRsp);
        params.remove("distrib");
        setDistributedParams(params);
        QueryResponse rsp = queryServer(json, params);
        compareResponses(rsp, controlRsp);
        return rsp;
    }

    protected QueryResponse queryServer(String json, SolrParams params) throws SolrServerException
    {
        // query a random server
        int which = r.nextInt(clients.size());
        SolrServer client = clients.get(which);
        QueryRequest request = getAlfrescoRequest(json, params);
        return request.process(client);
    }


}

