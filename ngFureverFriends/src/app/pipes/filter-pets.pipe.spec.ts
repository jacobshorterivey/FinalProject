import { FilterPetsPipe } from './filter-pets.pipe';

describe('FilterPetsPipe', () => {
  it('create an instance', () => {
    const pipe = new FilterPetsPipe();
    expect(pipe).toBeTruthy();
  });
});
