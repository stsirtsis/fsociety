import { TestBed, inject } from '@angular/core/testing';

import { ProviderService } from './providers.service';

describe('ProviderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProviderService]
    });
  });

  it('should be created', inject([ProviderService], (service: ProviderService) => {
    expect(service).toBeTruthy();
  }));
});
