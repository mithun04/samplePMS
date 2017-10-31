/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SamplePmsTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CFormDetailsDetailComponent } from '../../../../../../main/webapp/app/entities/c-form-details/c-form-details-detail.component';
import { CFormDetailsService } from '../../../../../../main/webapp/app/entities/c-form-details/c-form-details.service';
import { CFormDetails } from '../../../../../../main/webapp/app/entities/c-form-details/c-form-details.model';

describe('Component Tests', () => {

    describe('CFormDetails Management Detail Component', () => {
        let comp: CFormDetailsDetailComponent;
        let fixture: ComponentFixture<CFormDetailsDetailComponent>;
        let service: CFormDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SamplePmsTestModule],
                declarations: [CFormDetailsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CFormDetailsService,
                    JhiEventManager
                ]
            }).overrideTemplate(CFormDetailsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CFormDetailsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CFormDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CFormDetails(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.cFormDetails).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
