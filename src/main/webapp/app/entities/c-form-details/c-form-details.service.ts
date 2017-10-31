import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CFormDetails } from './c-form-details.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CFormDetailsService {

    private resourceUrl = SERVER_API_URL + 'api/c-form-details';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/c-form-details';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(cFormDetails: CFormDetails): Observable<CFormDetails> {
        const copy = this.convert(cFormDetails);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(cFormDetails: CFormDetails): Observable<CFormDetails> {
        const copy = this.convert(cFormDetails);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<CFormDetails> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to CFormDetails.
     */
    private convertItemFromServer(json: any): CFormDetails {
        const entity: CFormDetails = Object.assign(new CFormDetails(), json);
        entity.dateOfBirth = this.dateUtils
            .convertLocalDateFromServer(json.dateOfBirth);
        entity.passportExpirationDate = this.dateUtils
            .convertLocalDateFromServer(json.passportExpirationDate);
        entity.dateOfArrivalInIndia = this.dateUtils
            .convertLocalDateFromServer(json.dateOfArrivalInIndia);
        entity.visaEffectiveDate = this.dateUtils
            .convertLocalDateFromServer(json.visaEffectiveDate);
        entity.visaExpiryDate = this.dateUtils
            .convertLocalDateFromServer(json.visaExpiryDate);
        return entity;
    }

    /**
     * Convert a CFormDetails to a JSON which can be sent to the server.
     */
    private convert(cFormDetails: CFormDetails): CFormDetails {
        const copy: CFormDetails = Object.assign({}, cFormDetails);
        copy.dateOfBirth = this.dateUtils
            .convertLocalDateToServer(cFormDetails.dateOfBirth);
        copy.passportExpirationDate = this.dateUtils
            .convertLocalDateToServer(cFormDetails.passportExpirationDate);
        copy.dateOfArrivalInIndia = this.dateUtils
            .convertLocalDateToServer(cFormDetails.dateOfArrivalInIndia);
        copy.visaEffectiveDate = this.dateUtils
            .convertLocalDateToServer(cFormDetails.visaEffectiveDate);
        copy.visaExpiryDate = this.dateUtils
            .convertLocalDateToServer(cFormDetails.visaExpiryDate);
        return copy;
    }
}
