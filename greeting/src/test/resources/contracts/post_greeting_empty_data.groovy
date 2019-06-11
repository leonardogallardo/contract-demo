import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.RegexPatterns

import java.util.concurrent.ThreadLocalRandom

Contract.make {
    description "should return bad request"

    request {
        url "/greeting"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(
                name: value(consumer(""), producer("")),
                category: value(consumer(regex("^(?![123]\$)\\d+\$")), producer("4")),

        )
    }

    response {
        status BAD_REQUEST()
        headers {
            contentType applicationJson()
        }
        body(
                status: 'BAD_REQUEST',
                message: value(consumer("this is an error"), producer(regex(new RegexPatterns().nonBlank()))),
                errors: value(consumer(["err 1", "err 2"]))
        )
        bodyMatchers {
            jsonPath('$.errors', byCommand('assertThatJson($it.toString()).array().hasSize(2)'))
        }
    }
}
