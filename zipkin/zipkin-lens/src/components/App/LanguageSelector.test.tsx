/*
 * Copyright 2015-2020 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
import { fireEvent } from '@testing-library/react';
import React from 'react';

import render from '../../test/util/render-with-default-settings';
import { getLocale } from '../../util/locale';

import LanguageSelector, { LANGUAGES } from './LanguageSelector';

describe('<LanguageSelector />', () => {
  it('loads and displays button and no popover', async () => {
    const { getByTestId, queryByTestId } = render(<LanguageSelector />);

    const changeLanguageButton = getByTestId('change-language-button');
    const languageList = queryByTestId('language-list');

    expect(changeLanguageButton).toBeInTheDocument();
    expect(changeLanguageButton).toHaveAttribute('title', 'Change Language');

    expect(languageList).not.toBeInTheDocument();

    expect(getLocale()).toEqual('en');
  });

  it('click displays popover', async () => {
    const { findByTestId, getByTestId } = render(<LanguageSelector />);

    const changeLanguageButton = getByTestId('change-language-button');

    expect(changeLanguageButton).toBeInTheDocument();

    fireEvent.click(changeLanguageButton);

    const languageList = await findByTestId('language-list');

    expect(changeLanguageButton).toBeInTheDocument();
    expect(languageList).toBeInTheDocument();
    expect(languageList.children).toHaveLength(LANGUAGES.length);

    expect(getLocale()).toEqual('en');
  });

  it('language select changes locale and refreshes', async () => {
    const { findByTestId, getByTestId } = render(<LanguageSelector />);

    const changeLanguageButton = getByTestId('change-language-button');

    expect(changeLanguageButton).toBeInTheDocument();

    fireEvent.click(changeLanguageButton);

    await findByTestId('language-list');

    fireEvent.click(getByTestId('language-list-item-zh-cn'));

    await expect(window.location.reload).toHaveBeenCalled();

    expect(getLocale()).toEqual('zh-cn');
  });
});
