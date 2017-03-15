package edu.pl.pollub.service.implementation;

import edu.pl.pollub.entity.VerificationToken;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;
import edu.pl.pollub.repository.VerificationTokenRepository;
import edu.pl.pollub.service.VerificationTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dell on 2017-03-15.
 */
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;


    @Inject
    public VerificationTokenServiceImpl(final VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    @Transactional
    public VerificationToken save(@NotNull @Valid final VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public List<VerificationToken> getAllVerificationTokens() throws TableIsEmptyException {
        List<VerificationToken> tokens = verificationTokenRepository.findAll();
        if (tokens.size() == 0)
            throw new TableIsEmptyException("vtoken");
        return tokens;
    }

    @Override
    @Transactional
    public VerificationToken update(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public VerificationToken delete(VerificationToken token) {
        verificationTokenRepository.delete(token);
        return token;
    }

    @Override
    @Transactional
    public VerificationToken getById(Long id) throws ObjectNotFoundException {
        VerificationToken token = verificationTokenRepository.findOne(id);
        if (token == null) throw new ObjectNotFoundException(id);
        return token;
    }


    @Override
    public VerificationToken getByToken(String token) throws ObjectNotFoundException {
        VerificationToken vtoken = verificationTokenRepository.findByToken(token);
        if (vtoken == null) throw new ObjectNotFoundException(token);
        return vtoken;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String existingToken) {
        return new VerificationToken(UUID.randomUUID().toString(), (verificationTokenRepository.findByToken(existingToken)).getUser());
    }
}
