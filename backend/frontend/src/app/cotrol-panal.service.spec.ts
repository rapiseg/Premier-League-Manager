import { TestBed } from '@angular/core/testing';

import { ControlPanelService } from './control-panel.service';

describe('CotrolPanalService', () => {
  let service: ControlPanelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ControlPanelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
