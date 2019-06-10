import org.springframework.cloud.contract.spec.Contract

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
                name: "",
                category: ThreadLocalRandom.current().nextInt(4, Integer.MAX_VALUE)

        )
    }

    response {
        status BAD_REQUEST()
        headers {
            contentType applicationJson()
        }
        body(
                status: 'BAD_REQUEST',
                message: regex("(.*?)"),
                errors: []
        )
        bodyMatchers {
            jsonPath('$.errors', byCommand('assertThatJson($it.toString()).array().hasSize(2)'))

        }
    }
}
