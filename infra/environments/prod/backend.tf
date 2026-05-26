terraform {
    backend "gcs" {
        bucket      = "library-ops-prod-tf-state-538582187965"
        prefix      = "terraform/state"
        credentials = "./credentials/credentials.json"
    }
}