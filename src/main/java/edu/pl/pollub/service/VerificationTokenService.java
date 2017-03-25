package edu.pl.pollub.service;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.entity.VerificationToken;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
public interface VerificationTokenService {
    VerificationToken save(VerificationToken verificationToken);

    List<VerificationToken> getAllVerificationTokens() throws TableIsEmptyException;

    VerificationToken update(VerificationToken verificationToken);

    VerificationToken delete(VerificationToken role);

    VerificationToken getById(Long id) throws ObjectNotFoundException;

    VerificationToken generateNewVerificationToken(String existingToken);

    VerificationToken generateNewVerificationToken(User user);

    VerificationToken getByToken(String token) throws ObjectNotFoundException;
}
