<#import "/org/alfresco/utils/feed.utils.ftl" as feedLib/>
<#assign DISPLAY_ITEMS = 999>
<script type="text/javascript">//<![CDATA[
   new Alfresco.RssFeed("${args.htmlid}").setOptions(
   {
      "componentId": "${instance.object.id}",
      "feedURL": "${uri}", 
      "limit": "<#if limit?number != DISPLAY_ITEMS>${limit}<#else>all</#if>"      
   });
//]]></script>
<div class="dashlet rssfeed">
   <div class="title">${title!msg("label.header")}</div>
   <div class="toolbar">
       <a href="#" id="${args.htmlid}-configFeed-link" class="theme-color-1">${msg("label.configure")}</a>
   </div>
   <div class="body scrollableList" id="${args.htmlid}-scrollableList">
	<#if items?? && items?size &gt; 0>
		<#list items as item>
		   <#if item_index &lt; limit?number><@feedLib.renderItem item=item target=target/><#else><#break></#if>
		</#list>
	<#else>
      <div class="detail-list-item first-item last-item">
         <span>${msg("label.noItems")}</span>
      </div>
	</#if>
	</div>
</div>
