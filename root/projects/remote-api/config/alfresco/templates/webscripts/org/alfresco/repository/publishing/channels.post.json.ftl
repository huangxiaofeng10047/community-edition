<#-- Response to a request to create a publishing channel -->
<#escape x as jsonUtils.encodeJSONString(x)>
{
   "data": 
   {
      "channelId" : "${channelId}",
      "authoriseUrl": "${authoriseUrl}",
      "authCallbackUrl": "${authCallbackUrl}"
   }
}
</#escape>
