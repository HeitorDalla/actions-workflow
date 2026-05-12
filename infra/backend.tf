terraform {
    backend "gcs" {
        bucket      = "library-ops-dev-tf-state-824703218789"
        prefix      = "terraform/state"
        credentials = "./credentials/credentials.json"
    }
}