package br.com.felipec91.application.use_case.concept;

/*
 * @param I = Input class
 * @return O = Output class
 */
public interface UseCase<I, O> {

    O execute(I input);
}
