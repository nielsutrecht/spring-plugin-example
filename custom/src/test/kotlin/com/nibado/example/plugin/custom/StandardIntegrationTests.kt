package com.nibado.example.plugin.custom

import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["facade=standard"])
class StandardIntegrationTests : BaseIntegrationTests() {

}
