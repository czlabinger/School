import React from "react";

const ImprintComponent = () => {
  return (
    <div className="mt-[5rem] text-left justify-center items-center p-20">
      <h1 className="text-5xl">Imprint</h1>
      <p className="mt-[1rem] text-xl">Lorem Ipsum Lorem Ipsum Lorem Ipsum</p>

      <div>
        <div className="pt-10">
          <h2 className="text-3xl">VSA GmbH</h2>
          <section className="mb-6">
            <p>
              Max Mustermann
              <br />
              Musterstraße 1<br />
              12345 Musterstadt
              <br />
            </p>
          </section>
          <section className="mb-6">
            <h2 className="text-2xl font-semibold mb-2">Kontakt</h2>
            <p>
              <a className="font-bold">Telefon:</a> 01234/56789
              <br />
              <a className="font-bold">Fax:</a> 01234/56789-0
              <br />
              <a className="font-bold">E-Mail:</a> info@mustermann.de
              <br />
            </p>
          </section>
          <section className="mb-6">
            <h2 className="text-2xl font-semibold mb-2">Haftungsausschluss</h2>
            <div className="mb-4">
              <h3 className="text-xl font-medium mb-1">Haftung für Inhalte</h3>
              <p>
                Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene
                Inhalte auf diesen Seiten nach den allgemeinen Gesetzen
                verantwortlich. Nach §§ 8 bis 10 TMG sind wir als
                Diensteanbieter jedoch nicht verpflichtet, übermittelte oder
                gespeicherte fremde Informationen zu überwachen oder nach
                Umständen zu forschen, die auf eine rechtswidrige Tätigkeit
                hinweisen.
              </p>
              <p>
                Verpflichtungen zur Entfernung oder Sperrung der Nutzung von
                Informationen nach den allgemeinen Gesetzen bleiben hiervon
                unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem
                Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich.
                Bei Bekanntwerden von entsprechenden Rechtsverletzungen werden
                wir diese Inhalte umgehend entfernen.
              </p>
            </div>
            <div className="mb-4">
              <h3 className="text-xl font-medium mb-1">Haftung für Links</h3>
              <p>
                Unser Angebot enthält Links zu externen Websites Dritter, auf
                deren Inhalte wir keinen Einfluss haben. Deshalb können wir für
                diese fremden Inhalte auch keine Gewähr übernehmen. Für die
                Inhalte der verlinkten Seiten ist stets der jeweilige Anbieter
                oder Betreiber der Seiten verantwortlich. Die verlinkten Seiten
                wurden zum Zeitpunkt der Verlinkung auf mögliche Rechtsverstöße
                überprüft. Rechtswidrige Inhalte waren zum Zeitpunkt der
                Verlinkung nicht erkennbar.
              </p>
              <p>
                Eine permanente inhaltliche Kontrolle der verlinkten Seiten ist
                jedoch ohne konkrete Anhaltspunkte einer Rechtsverletzung nicht
                zumutbar. Bei Bekanntwerden von Rechtsverletzungen werden wir
                derartige Links umgehend entfernen.
              </p>
            </div>
            <div>
              <h3 className="text-xl font-medium mb-1">Urheberrecht</h3>
              <p>
                Die durch die Seitenbetreiber erstellten Inhalte und Werke auf
                diesen Seiten unterliegen dem deutschen Urheberrecht. Die
                Vervielfältigung, Bearbeitung, Verbreitung und jede Art der
                Verwertung außerhalb der Grenzen des Urheberrechtes bedürfen der
                schriftlichen Zustimmung des jeweiligen Autors bzw. Erstellers.
                Downloads und Kopien dieser Seite sind nur für den privaten,
                nicht kommerziellen Gebrauch gestattet.
              </p>
              <p>
                Soweit die Inhalte auf dieser Seite nicht vom Betreiber erstellt
                wurden, werden die Urheberrechte Dritter beachtet. Insbesondere
                werden Inhalte Dritter als solche gekennzeichnet. Sollten Sie
                trotzdem auf eine Urheberrechtsverletzung aufmerksam werden,
                bitten wir um einen entsprechenden Hinweis. Bei Bekanntwerden
                von Rechtsverletzungen werden wir derartige Inhalte umgehend
                entfernen.
              </p>
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default ImprintComponent;