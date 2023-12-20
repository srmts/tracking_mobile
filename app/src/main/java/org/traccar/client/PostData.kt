package org.traccar.client

data class Attributes(val username: String)
data class PostData(val name: String, val uniqueId: String, val attributes: Attributes)