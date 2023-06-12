package com.nibado.example.plugin.custom

import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["facade=custom"])
class CustomIntegrationTests : BaseIntegrationTests()
