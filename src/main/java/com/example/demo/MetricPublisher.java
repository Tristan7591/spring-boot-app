package com.example.demo;

import software.amazon.awssdk.regions.Region; // Import pour la région
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

public class MetricPublisher {

    public static void publishMetric(String metricName, double value) {
        // Créer un client CloudWatch avec une région spécifique
        CloudWatchClient cloudWatchClient = CloudWatchClient.builder()
                .region(Region.US_EAST_1) // Remplacez par votre région AWS (exemple : US_EAST_1)
                .build();

        MetricDatum datum = MetricDatum.builder()
                .metricName(metricName) // Nom de la métrique
                .unit(StandardUnit.COUNT) // Unité de la métrique
                .value(value) // Valeur de la métrique
                .build();

        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace("CustomNamespace") // Namespace personnalisé
                .metricData(datum)
                .build();

        try {
            // Publier la métrique dans CloudWatch
            cloudWatchClient.putMetricData(request);
            System.out.println("Metric published: " + metricName);
        } catch (Exception e) {
            // Gestion des erreurs
            System.err.println("Error publishing metric: " + e.getMessage());
        } finally {
            // Fermer le client CloudWatch
            cloudWatchClient.close();
        }
    }
}
