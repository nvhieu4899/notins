package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.Photo;
import go.nvhieucs.notins.model.PhotoByUser;
import go.nvhieucs.notins.model.PhotoByUserKey;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

public class PhotoRepositoryImpl extends SimpleCassandraRepository<Photo, UUID> implements PhotoRepository {
    private final PhotoByUserRepository photoByUserRepository;

    private final CassandraTemplate cassandraTemplate;

    /**
     * Create a new {@link SimpleCassandraRepository} for the given {@link CassandraEntityInformation} and
     * {@link CassandraTemplate}.
     *
     * @param metadata              must not be {@literal null}.
     * @param operations            must not be {@literal null}.
     * @param photoByUserRepository
     * @param cassandraTemplate
     */


    public PhotoRepositoryImpl(CassandraEntityInformation<Photo, UUID> metadata, CassandraOperations operations, PhotoByUserRepository photoByUserRepository, CassandraTemplate cassandraTemplate) {
        super(metadata, operations);
        this.photoByUserRepository = photoByUserRepository;
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public <S extends Photo> S insert(S entity) {
        final CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
        insertByUser(entity, batchOps);
        batchOps.insert(entity);
        batchOps.execute();
        return entity;
    }

    private void insertByUser(Photo photo, final CassandraBatchOperations batchOps) {
        batchOps.insert(
                new PhotoByUser(
                        new PhotoByUserKey(photo.getUserId(),
                                photo.getCreationDate(),
                                photo.getPhotoId()),
                        photo.getPhotoPath(),
                        photo.getPhotoLat(),
                        photo.getPhotoLng(),
                        photo.getUserLat(),
                        photo.getUserLng()));
    }

    @Override
    public void delete(Photo entity) {
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();
        deleteByUser(entity, batchOperations);
        batchOperations.delete(entity);
        batchOperations.execute();
    }

    private void deleteByUser(Photo photo, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(photoByUserRepository.findById(
                new PhotoByUserKey(photo.getUserId(), photo.getCreationDate(), photo.getPhotoId())));

    }
}
