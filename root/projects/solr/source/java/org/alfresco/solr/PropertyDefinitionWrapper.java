package org.alfresco.solr;

import java.util.List;
import java.util.Locale;

import org.alfresco.repo.dictionary.Facetable;
import org.alfresco.repo.dictionary.IndexTokenisationMode;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * @author Andy
 *
 */
public class PropertyDefinitionWrapper implements PropertyDefinition
{
    PropertyDefinition delegate;
    
    PropertyDefinitionWrapper(PropertyDefinition delegate)
    {
        this.delegate = delegate;
    }

    public ModelDefinition getModel()
    {
        return delegate.getModel();
    }

    public QName getName()
    {
        return delegate.getName();
    }

    public String getTitle()
    {
        return delegate.getTitle();
    }

    public String getTitle(MessageLookup messageLookup)
    {
        return delegate.getTitle(messageLookup);
    }

    public String getTitle(MessageLookup messageLookup, Locale locale)
    {
        return delegate.getTitle(messageLookup, locale);
    }

    public String getDescription()
    {
        return delegate.getDescription();
    }

    public String getDescription(MessageLookup messageLookup)
    {
        return delegate.getDescription(messageLookup);
    }

    public String getDescription(MessageLookup messageLookup, Locale locale)
    {
        return delegate.getDescription(messageLookup, locale);
    }

    public String getDefaultValue()
    {
        return delegate.getDefaultValue();
    }

    public DataTypeDefinition getDataType()
    {
        return delegate.getDataType();
    }

    public ClassDefinition getContainerClass()
    {
        return delegate.getContainerClass();
    }

    public boolean isOverride()
    {
        return delegate.isOverride();
    }

    public boolean isMultiValued()
    {
        return delegate.isMultiValued();
    }

    public boolean isMandatory()
    {
        return delegate.isMandatory();
    }

    public boolean isMandatoryEnforced()
    {
        return delegate.isMandatoryEnforced();
    }

    public boolean isProtected()
    {
        return delegate.isProtected();
    }

    public boolean isIndexed()
    {
        return delegate.isIndexed();
    }

    public boolean isStoredInIndex()
    {
        return delegate.isStoredInIndex();
    }

    public IndexTokenisationMode getIndexTokenisationMode()
    {
        return IndexTokenisationMode.BOTH;
    }

    public boolean isIndexedAtomically()
    {
        return delegate.isIndexedAtomically();
    }

    public List<ConstraintDefinition> getConstraints()
    {
        return delegate.getConstraints();
    }

    public String getAnalyserResourceBundleName()
    {
        return delegate.getAnalyserResourceBundleName();
    }

    public String resolveAnalyserClassName(Locale locale)
    {
        return delegate.resolveAnalyserClassName(locale);
    }

    public String resolveAnalyserClassName()
    {
        return delegate.resolveAnalyserClassName();
    }

    /* (non-Javadoc)
     * @see org.alfresco.service.cmr.dictionary.PropertyDefinition#getFacetable()
     */
    @Override
    public Facetable getFacetable()
    {
       return delegate.getFacetable();
    }

  
}
