package org.tosch.neverrest;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;

public class BaseElasticsearchConfig {
    @Value("${elasticsearch.cluster.nodes}")
    private String[] nodes;

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    @Bean
    public Client client() {
        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
        TransportClient transportClient = TransportClient.builder().settings(settings).build();

        for (String node : nodes) {
            String[] nodeParts = node.split(":");
            String host = nodeParts[0];
            int port = Integer.valueOf(nodeParts[1]);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
            TransportAddress transportAddress = new InetSocketTransportAddress(inetSocketAddress);
            transportClient.addTransportAddress(transportAddress);
        }

        transportClient.connectedNodes();
        return transportClient;
    }
}
