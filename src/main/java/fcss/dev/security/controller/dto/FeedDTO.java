package fcss.dev.security.controller.dto;

import java.util.List;

public record FeedDTO(List<FeedItensDTO> feedItens, int page, int pageSize, int totalPages, long totalElements) {}
