# kosl: Kubernetes Opinionated Service Layout

kosl is a toolset for defining a set of services in an opinionated way.

## Introduction

kosl intakes a workspace of multiple services, each with a kosl-server.json file, which defines how the service should be built and run.
kosl then provides a set of tools to build container images and generate Kubernetes resources.
