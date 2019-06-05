

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return person by id=1"

    request {
        url "/greeting"
        method POST()
        headers {
            contentType applicationJson()
        }
        body (
            name: "Toninho",
            category: 1
        )
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                message: "Oizinho Toninho. Seja bem vindo!"
        )
    }
}
