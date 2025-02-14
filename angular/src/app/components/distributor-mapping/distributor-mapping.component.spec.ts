import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistributorMappingComponent } from './distributor-mapping.component';

describe('DistributorMappingComponent', () => {
  let component: DistributorMappingComponent;
  let fixture: ComponentFixture<DistributorMappingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DistributorMappingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistributorMappingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
