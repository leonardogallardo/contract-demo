

import org.springframework.cloud.contract.spec.Contract

import java.util.concurrent.ThreadLocalRandom

Contract.make {
    description "Should return greeting message"

    request {
        url "/greeting"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
            name: anyNonEmptyString(),
            category: ThreadLocalRandom.current().nextInt(1, 3 + 1)

		)
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                message: regex("(.*?)")
        )
    }
}
