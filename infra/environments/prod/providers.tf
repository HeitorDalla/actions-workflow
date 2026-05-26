provider "google" {
    project = var.project_id
    region = var.gcp_region
    zone = var.gcp_zone
    credentials = var.credentials_file  
}