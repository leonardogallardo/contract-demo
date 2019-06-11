

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.RegexPatterns

Contract.make {
    description "Should return greeting message"

    request {
        url "/greeting"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
            name: value(consumer(regex(new RegexPatterns().nonBlank())), producer("leonardo")),
            category: value(consumer(regex("[1-3]")), producer("2")),

		)
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                message: value(consumer("random message"), producer(regex(new RegexPatterns().nonBlank())))
        )
    }
}
