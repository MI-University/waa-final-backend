package com.waa.backend.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class BlobStorageInfo {
    @Id
    String id;
    @Transient
    String fullPath;
    String relativePath;
    long size;
    String originalFileName;
}
